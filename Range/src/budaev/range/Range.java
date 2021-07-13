package budaev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if ((from <= range.from) && (to >= range.to)) {
            return this;
        }

        if ((from >= range.from) && (to <= range.to)) {
            return range;
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if ((to < range.from) || (range.to < from)) {
            return new Range[]{this, range};
        }

        Range newRange = new Range(from, to);
        newRange.setFrom(Math.min(from, range.from));
        newRange.setTo(Math.max(to, range.to));

        return new Range[]{newRange};
    }

    public Range[] getComplement(Range range) {
        if (this.getIntersection(range) == null){
            return null;
        }

        if (from >= range.from && to <= range.to) {
            return null;
        }

        if ((to < range.from) || (range.to < from)) {
            return new Range[]{this};
        }

        Range newRange1 = new Range(from, range.from);
        Range newRange2 = new Range(range.to, to);

        return new Range[]{newRange1, newRange2};
    }
}