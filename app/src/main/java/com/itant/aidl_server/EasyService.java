package com.itant.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.itant.aidl.Book;
import com.itant.aidl.IEasyLink;


/**
 * Created by zhanzc on 2017/9/20.
 */

public class EasyService extends Service {

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Toast.makeText(getApplicationContext(), message.obj.toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    private IEasyLink.Stub mBinder = new IEasyLink.Stub() {
        @Override
        public void testEasyLinkAIDL(String text) throws RemoteException {
            Message message = Message.obtain();
            message.obj = text;
            mHandler.sendMessage(message);
        }

        @Override
        public void anotherMethod(String text, Book book) throws RemoteException {
            Message message = Message.obtain();
            message.obj = text + book.getName();
            mHandler.sendMessage(message);
        }

    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
