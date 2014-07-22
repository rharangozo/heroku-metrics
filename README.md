# Build

Build the project with

    $ mvn install

# Configure

You will need to set the `REPO` environment variable, so the execution wrapper script knows where to find the maven dependencies. For example:

    $ export REPO=$HOME/.m2/repository

# Run

Now you can run your webapp with:

    $ sh target/bin/webapp 

(it does not work for me now, It is 'target\bin\webapp.bat' from windows shell)
(the wrapper script is not executable by default).


