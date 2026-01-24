package root.util;

import java.time.LocalDateTime;
import java.util.Random;

public class OtpUtil {
    // Tạo mã
    public static String otpCode(){
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
    // Thời gian tồn tại của mã
    public static LocalDateTime expiredAfterMinutes(int minutes){
        return LocalDateTime.now().plusMinutes(minutes);
    }
}
