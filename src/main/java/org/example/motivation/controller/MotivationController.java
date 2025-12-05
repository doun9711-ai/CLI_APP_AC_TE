package org.example.motivation.controller;

import org.example.motivation.entity.Motivation;

import java.util.*;

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

    public void delete(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[1]);

        if (cmdBits.length == 1) {
            System.out.println("명령어 확인하고 다시 써");
            return;
        }
        Motivation foundMotivation = null;
        int foundIndex = -1;

        for (int i = 0; i < motivations.size(); i++) {
            Motivation motivation = motivations.get(i);
            if (motivation.getId() == id) {
                foundIndex = i;
                foundMotivation = motivation;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }

        motivations.remove(foundIndex);
        System.out.println(id + "번 moti 삭제됨");
    }

    public void newDelete(String cmd) {
        Rq rq = new Rq(cmd);

        System.out.println("rq.getParams(\"id\") : " + rq.getParams("id"));

        int id = Integer.parseInt(rq.getParams("id"));

        Motivation foundMotivation = null;

        for (int i = 0; i < motivations.size(); i++) {
            Motivation motivation = motivations.get(i);
            if (motivation.getId() == id) {
                foundMotivation = motivation;
                break;
            }
        }

        if (foundMotivation == null) {
            System.out.println("해당 moti는 ArrayList에 없던데?");
            return;
        }

        motivations.remove(foundMotivation);
        System.out.println(id + "번 moti 삭제됨");

    }
}