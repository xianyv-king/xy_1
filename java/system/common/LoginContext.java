package system.common;

public class LoginContext {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static String getLoginUserId() {
        return THREAD_LOCAL.get();
    }

    public static void setLoginUserId(String id) {
        THREAD_LOCAL.set(id);
    }

    public static void removeLoginUserId() {
        THREAD_LOCAL.remove();
    }
}
