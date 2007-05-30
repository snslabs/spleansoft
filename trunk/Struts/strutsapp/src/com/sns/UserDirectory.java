package com.sns;

abstract public class UserDirectory {

    abstract public boolean isValidPassword(String username, String password);

    public static UserDirectory getInstance(){
        return createInstance();
    }


    private static UserDirectory createInstance() {
        UserDirectory directory = createRealUserDirectory();
        if(directory==null){
            directory = createFakeUserDirectory();
        }
        return directory;
    }

    private static UserDirectory createFakeUserDirectory() {
        return new UserDirectory() {
            public boolean isValidPassword(String username, String password) {
                return true;
            }
        };
    }

    private static UserDirectory createRealUserDirectory() {
        return null;
    }
}
