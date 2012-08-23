package springconversio.web;

import springconversio.web.WebController;

import junit.framework.TestCase;

public class WebControllerTests extends TestCase {

    public void testHandleRequestView() throws Exception {	
        WebController controller = new WebController();
        assertNotNull(controller);
    }
}
