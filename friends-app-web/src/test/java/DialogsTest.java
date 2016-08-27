import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.MessageRepository;
import com.cheliadina.service.MessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author nastya
 */
public class DialogsTest {

    private static final int CURRENT_USER_ID = 1;
    private static final int TEST_USER2_ID = 2;
    private static final int TEST_USER3_ID = 3;
    private static final int TEST_USER4_ID = 4;

    private MessageService messageService = new MessageService();

    private MessageRepository messageRepository;

    @Before
    public void executedBeforeEach(){
        messageRepository = Mockito.mock(MessageRepository.class);
        messageService.setMessageRepository(messageRepository);
    }

    @Test
    public void testGetDialogsNoMessages(){
        List<Message> allUserMessages = new ArrayList<>();
        Mockito
                .when(messageRepository.findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(CURRENT_USER_ID, CURRENT_USER_ID))
                .thenReturn(allUserMessages);
        Assert.assertTrue(messageService.getDialogs(CURRENT_USER_ID).isEmpty());

        Mockito.verify(messageRepository).findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(CURRENT_USER_ID, CURRENT_USER_ID);
    }

    @Test
    public void testGetDialogsManyDialogs(){
        User user1 = new User();
        user1.setId(CURRENT_USER_ID);
        user1.setFirstName("Mickey");

        User user2 = new User();
        user2.setId(TEST_USER2_ID);
        user2.setFirstName("John");

        User user3 = new User();
        user3.setId(TEST_USER3_ID);
        user3.setFirstName("Ivan");

        User user4 = new User();
        user4.setId(TEST_USER4_ID);
        user4.setFirstName("Mary");

        Message message1 = new Message("Hi John", user1, user2);
        Message message2 = new Message("Hello", user2, user1);

        Message message3 = new Message("Hi Mickey", user3, user1);
        Message message4 = new Message("Hi Ivan", user1, user3);
        Message message5 = new Message("How are you?", user3, user1);

        Message message6 = new Message("Hi Mary", user1, user4);

        List<Message> messages = Arrays.asList(message1, message2, message3, message4, message5, message6);

        Mockito.when(messageRepository.findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(CURRENT_USER_ID, CURRENT_USER_ID))
                .thenReturn(messages);

        List<Message> expectedDialogs = Arrays.asList(message6, message5, message2);
        List<Message> actualDialogs = messageService.getDialogs(CURRENT_USER_ID);

        Assert.assertEquals(expectedDialogs, actualDialogs);

        Mockito.verify(messageRepository).findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(CURRENT_USER_ID, CURRENT_USER_ID);
    }

}
