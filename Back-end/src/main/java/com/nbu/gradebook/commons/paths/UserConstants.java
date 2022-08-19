package com.nbu.gradebook.commons.paths;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserConstants {
    public static final String IS_ANONYMOUS = "isAnonymous()";
    public static final String IS_AUTHENTICATED = "isAuthenticated()";

    public static final String API = "/api";
    public static final String AUTHENTICATION = "/auth";
    public static final String SIGN_UP = "/signup";
    public static final String SIGN_IN = "/signin";
    public static final String ADMIN_PATH = "/admin";
    public static final String SCHOOL_PATH = "/school";
    public static final String CLASS_PATH = "/class";
    public static final String TEACHER_PATH = "/teacher";
    public static final String DIRECTOR_PATH = "/director";
    public static final String STUDENT_PATH = "/student";
    public static final String PARENT_PATH = "/parent";

    public static final String UNABLE_TO_FIND_USER_BY_ID_MESSAGE = "Unable to find user by id.";
    public static final String UNABLE_TO_FIND_USER_BY_NAME_MESSAGE = "Unable to find user by name.";
    public static final String UNABLE_TO_FIND_ROLE_BY_ID_MESSAGE = "Unable to find role by id.";
    public static final String UNABLE_TO_FIND_ROLE_BY_AUTHORITY_MESSAGE = "Unable to find role by authority.";
    public static final String UNABLE_TO_FIND_CLASS_BY_ID_MESSAGE = "Unable to find class by id.";

    public static final String UNABLE_TO_FIND_SCHOOL_BY_ID_MESSAGE = "Unable to find school by id.";
    public static final String UNABLE_TO_FIND_SCHOOL_BY_NAME_MESSAGE = "Unable to find school by name.";
    public static final String UNABLE_TO_FIND_DIRECTOR_BY_ID_MESSAGE = "Unable to find director by id.";
    public static final String UNABLE_TO_FIND_STUDENT_BY_ID_MESSAGE = "Unable to find student by id.";
    public static final String UNABLE_TO_FIND_PARENT_BY_ID_MESSAGE = "Unable to find parent by id.";
    public static final String UNABLE_TO_FIND_PARENT_BY_NAME_MESSAGE = "Unable to find parent by name.";
}
