import com.rafagao.dbook.domain.DbookMember;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author rafa gao
 */
@SpringBootTest
public class RedisTest {

//    @Autowired
    private RedisTemplate redisTemplate=new RedisTemplate();

    @Test
    public void redis(){
        DbookMember dbookMember=new DbookMember();
        dbookMember.setNickname("gao");
        dbookMember.setId(10086L);
        redisTemplate.opsForValue().set("dbook"+"01",dbookMember);

        Object o = redisTemplate.opsForValue().get("dbook" + "01");
        if(DbookMember.class.isInstance(o)){
            DbookMember o1 = (DbookMember) o;
            System.out.println("nickName:" +o1.getNickname()+'\n'+"id"+o1.getId());
        }
    }
}
