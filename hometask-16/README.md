**Test execution**

Locally or remotely:

    gradle clean test -Dhost=local
    gradle clean test -Dhost=remote



Add browser:

    browser.name=CHROME 
    browser.version=FIREFOX


Add browser version:

    CHROME 
    browser.version=99
    browser.version=100

    FIREFOX
    browser.version=97
    browser.version=98


Example:

    gradle clean test -Dhost=remote -Dbrowser.name=CHROME -Dbrowser.version=100 
