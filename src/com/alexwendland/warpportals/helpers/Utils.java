package com.alexwendland.warpportals.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.bukkit.Location;

import com.alexwendland.warpportals.objects.Coords;
import com.alexwendland.warpportals.objects.CoordsPY;

public class Utils {

	public static String readFile(String path, Charset encoding) throws IOException {
		Scanner scn = null;
		try {
			scn = new Scanner(new File(path));
			return scn.useDelimiter("\\Z").next();
		} finally {
			if (scn != null)
				scn.close();
		}
	}

	public static void copy(InputStream in, File file) throws IOException {
		OutputStream out = new FileOutputStream(file);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		in.close();
	}

	public static String[] ymlLevelCleanup(String[] orig, String startComb) {
		String[] comb = new String[orig.length];
		int i = 0;
		boolean fRun = true;
		for (String line : orig) {
			if (fRun) {
				comb[i] = line;
				fRun = false;
			} else {
				if (!line.startsWith(startComb))
					i++;
				comb[i] = (comb[i] != null ? comb[i] + "\n" : "") + line;
			}
		}
		return comb;
	}

	public static void coordsToLoc(Coords coords, Location loc) {
		loc.setWorld(coords.world);
		loc.setX(coords.x);
		loc.setY(coords.y);
		loc.setZ(coords.z);
	}

	public static void coordsToLoc(CoordsPY coords, Location loc) {
		loc.setWorld(coords.world);
		loc.setX(coords.x);
		loc.setY(coords.y);
		loc.setZ(coords.z);
		loc.setPitch(coords.pitch);
		loc.setYaw(coords.yaw);
	}

}
