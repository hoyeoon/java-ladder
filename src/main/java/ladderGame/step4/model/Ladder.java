package ladderGame.step4.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {

  public static final String MSG_ERROR_LIMIT_ROUND = "라운드는 최소 1이상 입니다.";

  private static final int LIMIT_HEIGHT = 1;

  private static final int START_INDEX = 0;

  private final List<Line> lines;

  private Ladder(final List<Line> lines) {
    this.lines = Collections.unmodifiableList(lines);
  }

  public static Ladder of(final int height, final int players) {
    validationHeight(height);

    return new Ladder(IntStream.range(START_INDEX, height)
        .mapToObj(i -> new Line(Line.createLine(players)))
        .collect(Collectors.toList()));
  }

  public List<List<Boolean>> ladderValues() {
    return lines.stream()
        .map(Line::lineValues)
        .collect(Collectors.toList());
  }

  public int findResult(final int userIndex) {

    int move = userIndex;
    for (Line line : lines) {
      move = line.pointMove(move);
    }
    return move;
  }

  private static void validationHeight(final int height) {
    if (height < LIMIT_HEIGHT) {
      throw new IllegalArgumentException(MSG_ERROR_LIMIT_ROUND);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Ladder ladder = (Ladder) o;
    return Objects.equals(lines, ladder.lines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lines);
  }


}