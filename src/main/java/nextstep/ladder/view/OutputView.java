package nextstep.ladder.view;

import nextstep.ladder.model.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OutputView {
    private static final String RESULT_FOR_LADDER_OUTPUT_MESSAGE = "\n사다리 결과\n";
    private static final String RESULT_FOR_USERS_OUTPUT_MESSAGE = "\n실행 결과";
    private static final String LADDER_HORIZONTAL_BAR = "─";
    private static final String LADDER_VERTICAL_BAR = "┃";
    private static final String BLANK = " ";
    private static final int TEXT_WIDTH = 5;

    private OutputView() {
    }

    public static void printLadderResult(Users users, Ladder ladder) {
        System.out.println(RESULT_FOR_LADDER_OUTPUT_MESSAGE);
        printUserNames(users);
        printLadder(ladder);
        // todo: 사다리 실행 결과 출력 구현
        System.out.println(
                Stream.of("꽝", "5,000", "꽝", "3,000")
                .map(name -> String.format("%6s", name))
                .collect(Collectors.joining())
        );
    }

    // todo: 사다리 실행 사용자 결과 구현
    public static void printExecutionResultForUsers(String command) {
        System.out.println(RESULT_FOR_USERS_OUTPUT_MESSAGE);
        Stream.of("pobi : 꽝", "honux : 3000", "crong : 꽝", "jk : 5000")
                .forEach(System.out::println);
    }

    private static void printUserNames(Users users) {
        System.out.println(userNames(users));
    }

    private static String userNames(Users users) {
        return users.getUsers()
                .stream()
                .map(User::getName)
                .map(name -> String.format("%6s", name))
                .collect(Collectors.joining());
    }

    private static void printLadder(Ladder ladder) {
        ladder.getLadders()
                .stream()
                .map(OutputView::ladderLine)
                .forEach(System.out::println);
    }

    private static String ladderLine(LadderLine ladderLine) {
        return ladderLine.getPoints()
                .stream()
                .map(LadderPoint::getDirection)
                .map(OutputView::ladderPoint)
                .collect(Collectors.joining());
    }

    private static String ladderPoint(PointDirection direction) {
        if (PointDirection.LEFT == direction) {
            return new String(new char[TEXT_WIDTH]).replace("\0", LADDER_HORIZONTAL_BAR) + LADDER_VERTICAL_BAR;
        }
        return new String(new char[TEXT_WIDTH]).replace("\0", BLANK) + LADDER_VERTICAL_BAR;
    }
}
