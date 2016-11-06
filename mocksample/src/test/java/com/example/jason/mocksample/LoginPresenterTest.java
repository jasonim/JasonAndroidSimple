package com.example.jason.mocksample;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jsson on 16/11/6.
 */
public class LoginPresenterTest {

    @Test
    public void testLogin() throws Exception {
        UserLoginTask mockLonginTask = mock(UserLoginTask.class);
        NetManagerWraper netManagerWraper = mock(NetManagerWraper.class);
        when(netManagerWraper.isConnected()).thenReturn(false);
        LoginPresenter loginPresenter = new LoginPresenter(mockLonginTask);
        loginPresenter.login("jason@gmail.com", "123456");

        verify(mockLonginTask).execute(anyString(), anyString(), any(NetworkCallBack.class));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数
                Object[] arguments = invocation.getArguments();

                //callback是第三个参数
                NetworkCallBack callback = (NetworkCallBack) arguments[2];

                callback.onSuccess(null);
                return null;
            }
        }).when(mockLonginTask).execute(anyString(), anyString(), any(NetworkCallBack.class));
    }

    @Test
    public void testSpy() {
        NetManagerWraper spy = spy(new NetManagerWraper());
        assertTrue(spy.isConnected());
        when(spy.isConnected()).thenReturn(false);
    }
}

class NetManagerWraper {
    boolean isConnected() {
        return NetManager.isConnected();
    }
}