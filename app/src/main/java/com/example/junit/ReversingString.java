package com.example.junit;

public class ReversingString {

    public String reverse(String text)
    {
        String rev="";
        for(int j=text.length();j>0;--j)
        {
            rev=rev+(text.charAt(j-1));
        }
        return rev;
    }
}
