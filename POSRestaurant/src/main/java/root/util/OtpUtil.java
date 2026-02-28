package root.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;

public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();

    public static String otpCode() {
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public static LocalDateTime expiredAfterMinutes(int minutes){
        return LocalDateTime.now().plusMinutes(minutes);
    }
}
