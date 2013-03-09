package edu.oswego.csc480_hci521_2013.client.overlay;

/**
 *
 */
public class Sigma {

    public static native void callSigma(String canvas, String json, int depth, int leaves) /*-{
        $wnd.init(canvas, json, depth, leaves);
    }-*/;
}
