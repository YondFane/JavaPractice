package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//实现远程接口，继承 java.rmi.server.UnicastRemoteObject 类
public class TestServiceImpl extends UnicastRemoteObject implements TestService {
    protected TestServiceImpl() throws RemoteException {
    }

    @Override
    public String hello(String name) throws RemoteException {
        return "Hello " + name + "!";
    }
}
