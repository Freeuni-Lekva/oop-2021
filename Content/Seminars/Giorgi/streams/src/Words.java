import api.ArraySequence;
import api.LinkedList;
import api.Sequence;

import java.io.*;

public class Words {
    public static Sequence<File> listFiles(File dir) {
        Sequence<File> all = new ArraySequence<>(dir.listFiles());
        Sequence<File> dirs = all.filter(File::isDirectory);
        Sequence<Sequence<File>> subdirs = dirs.map(Words::listFiles);
        Sequence<File> subdirFiles = Sequence.flatten(subdirs);
        return Sequence.flatten(subdirFiles, all.filter(f -> !f.isDirectory()));
    }

    public static void main(String[] args) {
        File root = new File("/Users/lekva/teach/freeuni/oop/oop-2021/Content/Seminars");
        Sequence<File> javaFiles = listFiles(root).filter(f -> f.getName().endsWith(".java"));
        Sequence<String> lines = Sequence.flatten(javaFiles.map(f -> {
            LinkedList<String> ret = new LinkedList<>();
            try {
                BufferedReader r = new BufferedReader(new FileReader(f));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    ret.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }));
        Sequence<String> words = Sequence.flatten(lines.map(l -> new ArraySequence<>(l.split("\\s+"))));
        for (String w : words) {
            System.out.println(w);
        }
    }
}
