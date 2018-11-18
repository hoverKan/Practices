package com.sino.test.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Hover Kan
 * @create 2018-11-17 下午10:07
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1、通道（Channel）：负责连接
 *      java.nio.channels.Channel 接口：
 *                  |--SelectableChannel
 *                         |--SocketChannel
 *                         |--ServerSocketChannel
 *                         |--DatagramChannel
 *
 *                         |--Pipe.SinkChannel
 *                         |--Pipe.SourceChannel
 *
 * 2、缓冲区（Buffer）：负责数据的存取
 *
 * 3、选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 *      SelectionKey: 表示 SelectableChannel 和 Selector 之间的注册关系。每次向选择器注册通道是就会选择一个事件（选择键）。
 *                    选择键包含两个表示为整数值的操作集。操作集的每一位都表示该键的通道所支持的一类可选择操作
 *
 *          1、当调用 register(Selector sel,int ops) 将通道注册选择器时，选择器对通道的监听事件，需要通过第二个参数ops指定。
 *          2、可以监听的事件类型（可使用SelectionKey的四个常量表示）：
 *              读：  SelectionKey.OP_READ    (1)
 *              写：  SelectionKey.OP_WRITE   (4)
 *              连接：SelectionKey.OP_CONNECT  (8)
 *              接受：SelectionKey.OP_ACCEPT   (16)
 *          3、若注册时不止监听一个事件，则可以使用"位或"操作符连接
 *              int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE
 *
 */
public class TestNonBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        // 1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        // 2、切换非阻塞模式
        sChannel.configureBlocking(false);

        // 3、分配数据给服务端
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4、发送数据给服务端
        buf.put((new Date().toString() + "\n").getBytes());
        buf.flip();
        sChannel.write(buf);

        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2、切换位非阻塞模式
        ssChannel.configureBlocking(false);

        //3、绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4、获取选择器
        Selector selector = Selector.open();

        //5、将通道注册到选择器上，并且指定"监听接受事件"
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6、轮询式的获取选择器上已经"准备就绪"的事件
        while (selector.select()>0) {
            //7、获取当前选择器中注册的"选择键（已就绪的监听事件）"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()){
                //8、获取准备"就绪"的事件
                SelectionKey sk = it.next();

                //9、判断具体是什么事件准备就绪
                if (sk.isAcceptable()){
                    //10、若"接受就绪"，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();

                    //11、切换位非阻塞模式
                    sChannel.configureBlocking(false);

                    //12、将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);

                } else if (sk.isReadable()){
                    //13、获取当前选择器上"读就绪"状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14、读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = sChannel.read(buf))>0 ){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                //15、取消选择键 SelectionKey
                it.remove();

            }
        }
    }

}
