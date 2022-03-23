package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        // 客户端调用
        TestService testService = (TestService) Naming.lookup("rmi://localhost:1200/testService");
        System.out.println(testService.hello("Jack"));
    }
}
