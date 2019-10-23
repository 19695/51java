package com.learn.exec.third.gof.builder;

/**
 * 电脑类
 *
 * 内部类：Builder
 *
 * @author Colm
 * @create 2019/10/21
 */
public class Computer {

    private String Cpu;
    private String Memory;
    private String hardDisk;

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String cpu) {
        Cpu = cpu;
    }

    public String getMemory() {
        return Memory;
    }

    public void setMemory(String memory) {
        Memory = memory;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public String toString() {
        return "[cpu:" + Cpu + " , memory:" + Memory + " , hard disk:" + hardDisk + "]\n";
    }

    // builder
    public static class Builder{
        private Computer computer = new Computer();

        public Builder setCpu(String cpu){
            computer.setCpu(cpu);
            return this;
        }

        public Builder setMemory(String memory){
            computer.setMemory(memory);
            return this;
        }

        public Builder setHardDisk(String hardDisk){
            computer.setHardDisk(hardDisk);
            return this;
        }

        public Computer build(){
            return computer;
        }
    }
}
