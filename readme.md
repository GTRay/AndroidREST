1. To configure the server URL:

   - Edit the Ip_address string value saved in res/values/strings.xml to the IP address of the server. 

2. To run the Android test cases through Android Studio:

   - You will need either an Android emulator or an Android device. 

   - The test cases are put into the androidTest/java folder, which are implemented in MainActivityTest and LoginActivityTest.

   - To run these tests, simply right click the corresponding item and select run.  

3. To setup Continuous Integration on Circle CI:

   - Fork and clone the repository to your local computer

   - Edit the Circle CI setting in circle.yml as you need

   - Visit https://circleci.com/dashboard and click add Projects

   - Choose the organization that you are a member of

   - Then choose a repo to add to CircleCI. A new build will be started each time a new commit is pushed to the repo.

4. More details about the implemented test cases:

   - There are six test cases implemented. Two for the Registration Activity and four for the Login Activity. By default, the newUserRegister test case is commented out. 

   - If you want to run the newUserRegister test case, please uncomment the corresponding function and change the name and email by incrementing the number after the username. By doing that, you create a new user which is not already existing in the server. 