package md.leonis.save.explorer;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Generate implements FilenameFilter {

    public static void main(String[] args) throws IOException {
        File file = new File("./results");
        File[] maps = file.listFiles(new Generate());

        List<Geo> geos = Arrays.stream(maps).map(Geo::new).collect(Collectors.toList());
/*        String json = new Gson().toJson(geos);
        System.out.println(json);
        for (Geo geo: geos) {
            System.out.println(geo.toCSV());
        }*/

        new Gson().toJson(geos, new FileWriter("results.json"));
        file = new File("results.csv");
        FileWriter writer = new FileWriter("results.csv");
        for(Geo geo: geos) {
            writer.write(geo.toCSV());
            writer.write("\n");
        }
        writer.close();

    }

    @Override
    public boolean accept(File dir, String name) {
        return name.startsWith("x");
    }
}
