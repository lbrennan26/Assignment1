import java.nio.file.CopyOption;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.*;
import java.nio.file.*;
public class CopyDirVisitor extends SimpleFileVisitor<Path> {

	  private final Path fromPath;
	    private final Path toPath;
	    private final CopyOption copyOption;

    public CopyDirVisitor(Path fromPath, Path toPath, CopyOption copyOption)
    {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.copyOption = copyOption;
    }

}
