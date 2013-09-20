package de.bht.fpa.mail.s769183.imapnavigation.view;

import java.io.File;
import java.io.IOException;

public class DeleteDatabase {

	public static void delete(final File f) throws IOException {
		if (!f.exists()) {
			System.out.println("Directory doesn't exist!");
		} else {
			if (f.isDirectory()) {
				if (f.list().length == 0) {
					f.delete();
					System.out.println("Directory is deleted!");
				} else {
					String files[] = f.list();

					for (String temp : files) {
						File dirFiles = new File(f, temp);
						delete(dirFiles);
					}

					if (f.list().length == 0) {
						f.delete();
						System.out.println("Directory is deleted!");
					}
				}
			} else {
				f.delete();
				System.out.println("File is deleted!");
			}
		}
	}
}
