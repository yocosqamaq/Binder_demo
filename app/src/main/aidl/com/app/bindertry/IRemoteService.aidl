// IRemoteService.aidl
package com.app.bindertry;
import com.app.bindertry.MyText;

// Declare any non-default types here with import statements

interface IRemoteService {
    //define methods
    String showText();
    MyText getMyText();

}