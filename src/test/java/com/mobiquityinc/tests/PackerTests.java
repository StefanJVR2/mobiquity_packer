package com.mobiquityinc.tests;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PackerTests {

    //To run this test please copy input-data.txt from the test resources directory to your C:\
    @Disabled
    @Test
    void testMethod() throws APIException {
        Packer.pack("C:\\input-data.txt");
    }

}
