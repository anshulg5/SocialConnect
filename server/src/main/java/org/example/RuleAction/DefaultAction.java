package org.example.RuleAction;

public enum DefaultAction implements ActionType {
    Test{
        @Override
        public Action getInstance() {
            return new Test();
        }
    };

    @Override
    public Action getInstance() {
        return null;
    }
}
