package com.hr.pr.service;

import org.eclipse.egit.github.core.PullRequest;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.*;

public class CyclicTimeStat {

    private SortedMap<Integer, List<Duration>> cyclicTimesGroupedByDay;
    private SortedMap<Integer, Long> avgCyclicTimes;

    public CyclicTimeStat() {
        cyclicTimesGroupedByDay = new TreeMap<Integer, List<Duration>>();
    }

    public void addMergedPullRequest(PullRequest pullRequest){
        Date mergedAt = pullRequest.getMergedAt();
        if(mergedAt == null) {
            throw new IllegalArgumentException("Pull request is not merged : ID:" + pullRequest.getId());
        }
        if(avgCyclicTimes!=null) {
            throw new IllegalStateException("Pull request cannot be added. Average cyclic times has already counted");
        }

        Integer day = new DateTime(mergedAt).dayOfMonth().get();
        Duration duration = new Duration(mergedAt.getTime() - pullRequest.getCreatedAt().getTime());

        if(cyclicTimesGroupedByDay.containsKey(day)){
            cyclicTimesGroupedByDay.get(day).add(duration);
        } else {
            LinkedList<Duration> list = new LinkedList<Duration>();
            list.add(duration);
            cyclicTimesGroupedByDay.put(day, list);
        }
    }

    public void countAvgCyclicTime() {
        avgCyclicTimes = new TreeMap<Integer, Long>();
        for(Map.Entry<Integer,List<Duration>> entry : cyclicTimesGroupedByDay.entrySet()){
            long avgCyclicTime = 0l;
            for(Duration duration : entry.getValue()) {
                avgCyclicTime += duration.getStandardHours();
            }
            avgCyclicTimes.put(entry.getKey(), avgCyclicTime / entry.getValue().size());
        }
        cyclicTimesGroupedByDay = null;
    }

    public SortedMap getAvgCyclicTimes() {
        return Collections.unmodifiableSortedMap(avgCyclicTimes);
    }
}
