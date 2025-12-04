package org.example.motivation.controller;

import org.example.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MotivationController {

    int lastId = 0; // 몇 번까지 썼더라?
    List<Motivation> motivations = new ArrayList<>(); // motivation 저장소

    private Scanner sc;

    public MotivationController(Scanner sc) {
        this.sc = sc;
    }

    public void add() {
        int id = lastId + 1;
        System.out.print("body : ");
        String body = sc.nextLine();
        System.out.print("source : ");
        String source = sc.nextLine();

        Motivation motivation = new Motivation(id, body, source);

        motivations.add(motivation);

        System.out.printf("%d번 motivation이 등록되었습니다\n", id);
        lastId++;
    }

    public void list() {
        System.out.println("=".repeat(40));
        System.out.printf("   번호   /    source      /    body   \n");

        if (motivations.size() == 0) {
            System.out.println("등록된거 없음 xxxxx");
            return;
        }
        for (int i = motivations.size() - 1; i >= 0; i--) {
            Motivation motivation = motivations.get(i);

            if (motivation.getSource().length() > 7) {
                System.out.printf("   %d     /    %s   /    %s   \n", motivation.getId(), motivation.getSource().substring(0, 7) + "...", motivation.getBody());
                continue;
            }
            System.out.printf("   %d     /    %s     /    %s   \n", motivation.getId(), motivation.getSource(), motivation.getBody());
        }
        System.out.println("=".repeat(40));
    }
}