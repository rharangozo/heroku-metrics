package com.hr.pr.service;

import com.hr.pr.domain.Authenticator;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.SortedMap;

@Service
public class StatisticsService {

    /**
     * Calculates the cycle time of the latest pull requests on the given repositories
     *
     * @param authenticator For authentication
     * @param repositories
     * @return SortedMap. Days are assigned to the keys, average cycle times are assigned to the values
     */
    public SortedMap<Integer, Integer> processStatistics(Authenticator authenticator, com.hr.pr.domain.Repository... repositories) {
        try {
            GitHubClient client = new GitHubClient();
            authenticator.authenticate(client);

            RepositoryService rs = new RepositoryService(client);
            PullRequestService pullRequestService = new PullRequestService(client);

            CyclicTimeStat stat = new CyclicTimeStat();
            for (com.hr.pr.domain.Repository repo : repositories) {
                Repository repository = rs.getRepository(repo.getOwner(), repo.getName());

                for (PullRequest pr : pullRequestService.getPullRequests(repository, "closed")) {
                    //If the pull request object does not store the 'mergedAt' parameter, then it
                    //submit the request for this redundantly.
                    //The isMerged and the getMergedAt == null are not the same!
                    if (pr.getMergedAt() != null) {
                        stat.addMergedPullRequest(pr);
                    }
                }
            }

            stat.countAvgCyclicTime();
            return stat.getAvgCyclicTimes();

        } catch (IOException e) {
            //TODO: raise informative exception
            throw new RuntimeException(e);
        }
    }
}
