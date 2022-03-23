package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
// 接口实现Remote接口，方法必须抛出RemoteException异常
public interface TestService extends Remote {

    String hello(String name) throws RemoteException;

}
