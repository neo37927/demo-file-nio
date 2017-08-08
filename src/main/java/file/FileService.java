package file;

/**
 * Created by xiaolin  on 2017/8/8.
 */
public interface FileService {
    Object readFileByPath(String path);

    Object readFileByIdCertNo(String idCertNo);
}
