package org.example.motivation.controller;

import org.example.motivation.entity.Motivation;

import java.util.*;

public class MotivationController {

    int lastId = 0; // 몇 번까지 썼더라?
    List<Motivation> motivations = new ArrayList<>(); // motivation 저장소

    private Scanner sc;

    public MotivationController(Scanner sc) {
        this.sc = sc; //메인에서 받은 sc값을 다시 여기 sc에 넣는다
    }

    public void add() {
        int id = lastId + 1;
        System.out.print("body : ");
        String body = sc.nextLine(); //body 값은 sc를 통해 받겠다
        System.out.print("source : ");
        String source = sc.nextLine();

        Motivation motivation = new Motivation(id, body, source); //sc를 통해 받은 값을 저장하기위해 새로운객체 Motvation을 만든다

        motivations.add(motivation); //객체 motivations 에 motivation 값을 더한다(저장한다)

        System.out.printf("%d번 motivation이 등록되었습니다\n", id);
        lastId++; //하나의 등록이 끝나면 다음 번호로 넘어가기위해 lastid 값을 1더해준다
    }

    public void list() {
        System.out.println("=".repeat(40));
        System.out.printf("   번호   /    source      /    body   \n");

        if (motivations.size() == 0) { //motivations 객체 내부의 크기가 0이라면 등록된거 없음으로 출력하도록 한다
            System.out.println("등록된거 없음 xxxxx");
            return;
        }
        for (int i = motivations.size() - 1; i >= 0; i--) { //int i는 motivations의 크기에 -1이고  i가 0보다 크거나 같다면
            Motivation motivation = motivations.get(i); //motivation 의 값은 i번째 motivations 의 값을 꺼내온것을 넣는다

            if (motivation.getSource().length() > 7) { //motivation에서 꺼내온 source문자열의 길이가 7이상이라면
                System.out.printf("   %d     /    %s   /    %s   \n", motivation.getId(),  motivation.getSource().substring(0, 7) + "...", motivation.getBody());//motivationt 값을 0번 부터 7번까지 잘라서 뒤에 ...을 붙인 상태로 출력한다
                continue;
            }
            System.out.printf("   %d     /    %s     /    %s   \n", motivation.getId(), motivation.getSource(), motivation.getBody());  //그게 아니라면 continue 를 통해 스킵하고 원래 내용을 출력
        }
        System.out.println("=".repeat(40));
    }

    public void delete(String cmd) {
        String[] cmdBits = cmd.split(" ");      //명령어를 받아서 split 한다  공백을 기준으로
        int id = Integer.parseInt(cmdBits[1]);         // 1번 배열에 있는 값을 정수화 하여 id 값에 넣는다

        if (cmdBits.length == 1) {      //cmdBits 배열의 길이가 1이라면 즉 쪼개지지 않았을때
            System.out.println("명령어 확인하고 다시 써"); //지금은 App에서 명령어가 맞지 않을때 사용불가 명령어로 출력되기 때문에 필요없다
            return;
        }
        Motivation foundMotivation = null;
        int foundIndex = -1;

        for (int i = 0; i < motivations.size(); i++) {  //객체 내부의 크기가 i보다 크다면
            Motivation motivation = motivations.get(i); // i번째 motivations 의 값을 꺼내온다?
            if (motivation.getId() == id) { //만약 motivation에서 꺼낸 id값이 id와 같다면
                foundIndex = i; //
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