
package com.wuzz.demo.provider;

import com.wuzz.demo.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        return "[echo] Hello, " + message;
    }

}
