package root.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

public class OtpUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    // Tạo mã
    public static String otpCode(){
        return String.valueOf(100000 + RANDOM.nextInt(900000));
    }
    // Thời gian tồn tại của mã
    public static LocalDateTime expiredAfterMinutes(int minutes){
        return LocalDateTime.now().plusMinutes(minutes);
    }
}
