package annotations;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class HasTempFolder2 {
    /**
     * 使用@Rule注解的字段或方法必须为public
     * 否则会产生：org.junit.internal.runners.rules.ValidationError: The @Rule 'folder' must be public.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFile = folder.newFile("myfile.txt");
        File createdFolder = folder.newFolder("subfolder");
        // ...
    }
}
