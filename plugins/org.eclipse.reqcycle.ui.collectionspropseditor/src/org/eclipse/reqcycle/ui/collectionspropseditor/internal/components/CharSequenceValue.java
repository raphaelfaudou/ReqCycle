package org.eclipse.reqcycle.ui.collectionspropseditor.internal.components;

public class CharSequenceValue implements ItemValue<CharSequence> {

    private CharSequence value;

    public CharSequenceValue() {
        this(null);
    }

    public CharSequenceValue(CharSequence value) {
        this.value = value;
    }

    @Override
    public CharSequence getValue() {
        return this.value;
    }

    @Override
    public void setValue(CharSequence value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
