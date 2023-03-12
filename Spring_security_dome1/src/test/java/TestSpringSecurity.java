import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestSpringSecurity {
    @Test
    public void test(){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String s=encoder.encode("123");
        System.out.println(s);//实际密码为123
        //$2a$10$RJ6A.jd1tGWYPExPKINkJ.o6X2hl81AL8xYxm0yf5W3kGhLrEy8Gu
        //$2a$10$DubL0vTzkIBIedWxhBLB3OnUJ1Z1Q.YkHfTvnScZp.O1oDoUbCXLe

    }

}
