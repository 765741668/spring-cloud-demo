package com.hooray.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.hooray.common.CommConstants.FILE_UNIT_SIZE;

/**
 * 描述
 *
 *@author  Orochi-Yzh
 *@dateTime  2017/11/9 10:37
 *@updatetor
 */
@SuppressWarnings("ALL")
public class FileUtils {

    private static final Logger LOOGER = LoggerFactory.getLogger(FileUtils.class);

    public static void createFile(Path path) {
        try {
            if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            LOOGER.error("Cause Exception when createFile.", e);
        }

    }

    public static void copyFile(Path source, Path target) {
        try {
            // REPLACE_EXISTING:if EXIST-> replace,if not EXIST -> create
            // COPY_ATTRIBUTES: if EXIST-> replace,if not EXIST -> NoFoundException
            Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            LOOGER.error("Cause Exception when copyFile.", e);
        }
    }

    public static void transferToFile(Path source, Path target) {
        try (FileChannel inc = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel ouc = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            inc.transferTo(0, inc.size(), ouc);
        } catch (IOException e) {
            LOOGER.error("Cause Exception when transferToFile.", e);
        }
    }

    public static void transferFromFile(Path source, Path target) {
        try (FileChannel inc = FileChannel.open(source, StandardOpenOption.READ);
             FileChannel ouc = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            ouc.transferFrom(inc, 0, inc.size());
        } catch (IOException e) {
            LOOGER.error("Cause Exception when transferFromFile.", e);
        }
    }

    public static void move(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            LOOGER.error("Cause Exception when move.", e);
        }
    }


    public static void delete(Path path) {
        try {
            if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
                Files.delete(path);
            }
        } catch (IOException e) {
            LOOGER.error("Cause Exception when delete.", e);
        }
    }

    public static void writeByFilesAeadAllLines(Path source, Path target) {
        try {
            // if not exist throw NoSuchFileException
            List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);
            // if not exist throw NoSuchFileException
            Files.write(target, lines, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            LOOGER.error("Cause Exception when writeByFilesAeadAllLines.", e);
        }
    }

    public static void writeByFilesAeadAllLines(Path path, String fileName, String[] data) {
        try {
            // if not exist throw NoSuchFileException
            long start1 = System.currentTimeMillis();
            Files.createDirectories(path);

            Files.write(Paths.get(path + File.separator + fileName), Arrays.asList(data),
                    StandardCharsets.UTF_8, StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println(System.currentTimeMillis() - start1);
        } catch (IOException e) {
            LOOGER.error("Cause Exception when writeByFilesAeadAllLines.", e);
        }
    }

    public static List<String> readLine(Path source){
        //存储读取的每行数据
        List<String> dataList = new ArrayList<>();
        byte[] lineByte;
        //缓冲1024个字节
        ByteBuffer rBuffer = ByteBuffer.allocate(1024);

        try (FileChannel inc = FileChannel.open(source, StandardOpenOption.READ)) {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];

            //fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
            while (inc.read(rBuffer) != -1) {
                //读取结束后的位置，相当于读取的长度
                int rSize = rBuffer.position();
                //用来存放读取的内容的数组
                byte[] bs = new byte[rSize];
                //将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
                rBuffer.rewind();
                //相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
                rBuffer.get(bs);
                rBuffer.clear();

                int startNum = 0;
                //换行符
                int lf = 10;
                //回车符
                int cr = 13;
                //是否有换行符
                boolean hasLF = false;
                for(int i = 0; i < rSize; i++){
                    if(bs[i] == lf){
                        hasLF = true;
                        int tempNum = temp.length;
                        int lineNum = i - startNum;
                        //数组大小已经去掉换行符
                        lineByte = new byte[tempNum + lineNum];

                        //填充了lineByte[0]~lineByte[tempNum-1]
                        System.arraycopy(temp, 0, lineByte, 0, tempNum);
                        temp = new byte[0];
                        //填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]
                        System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);

                        //遇到空行，过滤回车符和换行符并忽略掉此行
                        if(lineByte.length <= 0){
                            if(i + 1 < rSize && bs[i + 1] == cr){
                                startNum = i + 2;
                            }else{
                                startNum = i + 1;
                            }
                            continue;
                        }

                        //lineByte.length-1 : 去掉\r转义符
                        //一行完整的字符串(过滤了换行和回车)
                        String line = new String(lineByte, 0, lineByte.length-1, "UTF-8");
                        dataList.add(line);

                        //读取成功后，更新新行的起始位置
                        if(i + 1 < rSize && bs[i + 1] == cr){
                            startNum = i + 2;
                        }else{
                            startNum = i + 1;
                        }

                    }
                }
                if(hasLF){
                    temp = new byte[bs.length - startNum];
                    System.arraycopy(bs, startNum, temp, 0, temp.length);
                }else{
                    //兼容单次读取的内容不足一行的情况
                    byte[] toTemp = new byte[temp.length + bs.length];
                    System.arraycopy(temp, 0, toTemp, 0, temp.length);
                    System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                    temp = toTemp;
                }
            }
            //兼容文件最后一行没有换行的情况
            if(temp.length > 0){
                String line = new String(temp, 0, temp.length, "UTF-8");
                dataList.add(line);
            }
        } catch (IOException e) {
            LOOGER.error("Cause Exception when readLine.", e);
        }

        return dataList;
    }


    /**
     * 使用管道写文件
     * @param source
     * @param target
     */
    public static void writeByFileChanal(Path source, Path target) {
        try (FileChannel ouc = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // read/write will be throws exception if not exist path or file
            try (FileChannel inc = FileChannel.open(source, StandardOpenOption.READ)) {

                ByteBuffer bb = ByteBuffer.allocate((int) inc.size());
                int br = inc.read(bb);
                while (br != -1) {
                    bb.flip();
                    while (bb.hasRemaining()) {
                        ouc.write(bb);
                    }
                    bb.clear();
                    br = inc.read(bb);
                }
            }

        } catch (IOException e) {
            LOOGER.error("Cause Exception when writeByFileChanal.", e);
        }

    }

    /**
     * 使用管道写文件
     * @param data
     * @param target
     */
    public static void writeByFileChanal(Path target,String data,StandardOpenOption openOption) {
        try (FileChannel ouc = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, openOption)) {
            ouc.write(ByteBuffer.wrap(data.getBytes()));
        } catch (IOException e) {
            LOOGER.error("Cause Exception when writeByFileChanal.", e);
        }

    }

    /**
     * 获取目录列表
     *
     * @param path
     * @return
     */
    private static List<String> getDirs(Path path) {
        LinkedList<String> list = new LinkedList<>();
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        String path = dir.getParent() + File.separator + dir.getFileName();
                        list.add(path);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                LOOGER.error("Cause Exception when getFiles.", e);
            }
        }

        return list;
    }

    /**
     * 获取文件列表
     *
     * @param path
     * @return
     */
    private static List<String> getFiles(Path path) {
        LinkedList<String> list = new LinkedList<>();
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        String path = dir.getParent() + File.separator + dir.getFileName();
                        list.add(path);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                LOOGER.error("Cause Exception when getFiles.", e);
            }
        }

        return list;
    }

    /**
     * 获取当前目录下的文件Path
     *
     * @param path
     * @return
     */
    public static List<Path> getFilesPath(Path path) {
        LinkedList<Path> list = new LinkedList<>();
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        String path = dir.getParent() + File.separator + dir.getFileName();
                        list.add(Paths.get(path));
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                LOOGER.error("Cause Exception when getFilesPath.", e);
            }
        }

        return list;
    }

    /**
     * 监听文件变化
     *
     * @param path
     */
    public static void event(Path path) {
        try (WatchService watch = FileSystems.getDefault().newWatchService()) {
            WatchKey key = null;
            for (String str : getDirs(path)) {
                key = Paths.get(str).register(watch, StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);
            }
            if (key != null) {
                while (true) {
                    key = watch.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.println("event kind --> " + event.kind() + " --> " + event.context());
                    }
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }

                }
            } else {
                System.err.println("Watchkey is null  ");
                LOOGER.error("Watchkey is null");
            }
        } catch (IOException | InterruptedException e) {
            LOOGER.error("Cause Exception when getFiles.", e);
        }

    }

    /**
     * 文件内容加密MD5
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream fi = null;
        byte[] buffer = new byte[8192];
        int len;

        try {
            digest = MessageDigest.getInstance("MD5");
            fi = new FileInputStream(file);

            while ((len = fi.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            BigInteger bigInt = new BigInteger(1, digest.digest());

            return bigInt.toString(16);
        } catch (Exception e) {
            LOOGER.error("Cause Exception when getFileMD5.", e);
            return null;
        }
    }

    /**
     * 文件内容加密SHA_1
     * @param file
     * @return
     */
    public static String getfilesha1(File file) {
        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream fi = null;
        byte[] buffer = new byte[8192];
        int len;

        try {
            digest = MessageDigest.getInstance("SHA-1");
            fi = new FileInputStream(file);

            while ((len = fi.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }

            BigInteger bigInt = new BigInteger(1, digest.digest());

            return bigInt.toString(16);
        } catch (Exception e) {
            LOOGER.error("Cause Exception when getFileSHA_1.", e);
            return null;
        }
    }


    /**
     * 获取文件容量单位
     * @param size
     * @return
     */
    public static String getSizeUnit(long size) {
        String result = "0";
        // DecimalFormat df = new DecimalFormat("###.00");
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        double isKB = size / 1024.0;
        double isMB = size / 1024.0 / FILE_UNIT_SIZE;
        double isGB = size / 1024.0 / FILE_UNIT_SIZE / FILE_UNIT_SIZE;
        if (size < FILE_UNIT_SIZE && size >= 1) {
            result = nf.format(size) + " Byte";
        }
        if (isKB < FILE_UNIT_SIZE && isKB >= 1) {
            result = nf.format(isKB) + " KB";
        }
        if (isMB < FILE_UNIT_SIZE && isMB >= 1) {
            result = nf.format(isMB) + " MB";
        }
        if (isGB < FILE_UNIT_SIZE && isGB >= 1) {
            result = nf.format(isGB) + " GB";
        }

        return result;
    }

    /**
     * 文件分割
     * @param sourceFile
     * @param desDir
     * @param limit
     */
    public static void splitFile(Path sourceFile, Path desDir, int limit) {
        try (FileChannel intFc = FileChannel.open(sourceFile, StandardOpenOption.READ)) {

            if (Files.notExists(desDir, LinkOption.NOFOLLOW_LINKS)) {
                Files.createDirectory(desDir);
            }

            // 100M split
            ByteBuffer bb = ByteBuffer.allocate(limit);
            String sourceFileName = sourceFile.getFileName().toString();
            int br = intFc.read(bb);
            int fileNameSequence = 0;
            while (br != -1) {
                bb.flip();
                while (bb.hasRemaining()) {
                    ++fileNameSequence;
                    String desFileName = String.valueOf(fileNameSequence) + sourceFileName.substring(sourceFileName.lastIndexOf("."));
                    Path newdesDir = Paths.get(desDir.getParent() + File.separator + desDir.getFileName() + File.separator + desFileName);
                    FileChannel outFc = FileChannel.open(newdesDir, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    outFc.write(bb);
                    outFc.close();
                }
                bb.clear();
                br = intFc.read(bb);
            }

        } catch (IOException e) {
            LOOGER.error("Cause Exception when splitFile.", e);
        }
    }

}
