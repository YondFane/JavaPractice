package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        // 启动服务
        LocateRegistry.createRegistry(1200);
        Naming.bind("rmi://localhost:1200/testService", new TestServiceImpl());
    }
}
