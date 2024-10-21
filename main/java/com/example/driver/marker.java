package com.example.driver;

import com.example.RWC.ReservedWordCounter;

import java.util.HashMap;

public class marker {
    /**
     * @param txtparse the array output from the txtparser
     * @param Reflection the HashMap output of the ReservedWordCounter
     * @param keys Hashmap of Methodnames and the paramter keys
     * @return array of MarkandComment Objects
     */
    public static GradeObject mark(String[][] txtparse, HashMap<String,ReservedWordCounter> Reflection, HashMap<String,Parameters> keys)
    {
        GradeObject tObject = parsemarker(txtparse);
        GradeObject rObjects = reflectionMarker(Reflection,keys);

        return new GradeObject(tObject,rObjects);
    }

    /**
     *
     * @param txtparse the array output from the txtparser
     * @return array of MarkandComment Objects
     */
    public static GradeObject parsemarker(String[][] txtparse){
        GradeObject tObject = new GradeObject(txtparse.length);
        for(int i = 0; i < txtparse.length; i++)
        {

            Question q = new Question(txtparse[i]);
            tObject.setMethodName(i,q.getQname());
            tObject.setComment(i,String.format("%s(%s)\n\t%s:  %s\n",q.getQname(),q.getParameter(),q.getExample(),q.getStudentAnswer()));
            tObject.setGrade(i,q.evaluate());
            tObject.setTotalGrade(i,q.getMarks());
        }
        return tObject;
    }

    /**
     *
     * @param reflection HashMap output of the ReservedWordCounter
     * @param parameterMap Hashmap of acceptable number of each keyword in order
     *                 0. Break
     *                 1. Continue
     *                 2. Return
     * @return array of MarkandComment Objects
     */
    public static GradeObject reflectionMarker(HashMap<String,ReservedWordCounter> reflection,HashMap<String,Parameters> parameterMap)
    {
        GradeObject rObject = new GradeObject(parameterMap.size());
        String[] s= new String[parameterMap.size()];
        parameterMap.keySet().toArray(s);
        int mark;
        String message;
        for(int i = 0; i < s.length; i++)
        {
            message = "";
            mark = 1;
            ReservedWordCounter r = reflection.get(s[i]);
            Parameters p = parameterMap.get(s[i]);
            if(r.getNumBreaks() > p.getBreaks())
            {
                mark = 0;
                message += String.format("\tMaximum number of break statements allowed is %d, you had %d\n",p.getBreaks(),r.getNumBreaks());
            }
            if(r.getNumContinue() > p.getContinues())
            {
                mark = 0;
                message += String.format("\tMaximum number of continue statements allowed is %d, you had %d\n",p.getContinues(),r.getNumContinue());
            }
            if(r.getNumReturn() > p.getReturns())
            {
                mark = 0;
                message += String.format("\tMaximum number of return statements is %d, you had %d\n",p.getReturns(),r.getNumReturn());
            }
            switch (mark) {
                case (1) -> rObject.setComment(i,String.format("All Ok in method %s\n", s[i]));
                case (0) ->
                        rObject.setComment(i,String.format("Error in method %s:\n %s", s[i], message));
            }
            rObject.setGrade(i,mark);
            rObject.setTotalGrade(i,1);
            rObject.setMethodName(i,s[i]);


        }
        return rObject;
    }


}
class Question {
    private final String qname;
    private final String example;
    private final String studentAnswer;
    private final int marks;
    private final String parameter;

    /**
     *
     * @param Q-Array of TxtParser String in form
     *         {"MethodName","Parameter","ExampleAnswer","StudentAnswer","Mark"}
     */
    public Question(String[] Q){
        this.qname = Q[0];
        this.parameter = Q[1];
        this.example = Q[2];
        this.studentAnswer = Q[3];
        this.marks = 1;
    }

    /**
     * evaluates questions
     * @return int mark
     */
    public int evaluate()
    {
        int mark = 0;
        if(this.example.equals(this.studentAnswer))
            mark = this.marks;
        return mark;
    }
    public int getMarks()
    {
        return this.marks;
    }

    public String getQname() {
        return qname;
    }

    public String getExample() {
        return example;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public String getParameter() {
        return parameter;
    }
}
class GradeObject{
     final int[] totalGrade;
     final String[] comment;
     final int[] grade;
     final String[] methodName;
    public GradeObject(int size){
        totalGrade = new int[size];
        this.grade = new int[size];
        this.comment = new String[size];
        this.methodName = new String[size];
    }
    public GradeObject(GradeObject tObject, GradeObject rObject)
    {
        this.totalGrade = new int[rObject.getSize()+ tObject.getSize()];
        this.comment = new String[rObject.getSize()+ tObject.getSize()];
        this.grade = new int[rObject.getSize()+ tObject.getSize()];
        this.methodName = new String[rObject.getSize()+tObject.getSize()];
        for(int i = 0; i < tObject.getSize(); i++)
        {
            this.totalGrade[i] = tObject.getTotalGrade(i);
            this.grade[i] = tObject.getGrade(i);
            this.comment[i] = tObject.getComment(i);
            this.methodName[i] = tObject.getMethodName(i);
        }
        for(int i = 0; i < rObject.getSize(); i++)
        {
            this.totalGrade[i+tObject.getSize()] = rObject.getTotalGrade(i);
            this.grade[i+tObject.getSize()] = rObject.getGrade(i);
            this.comment[i+tObject.getSize()] = rObject.getComment(i);
            this.methodName[i+tObject.getSize()] = rObject.getMethodName(i);
        }

    }

    public String getMethodName(int i)
    {
        return methodName[i];
    }
    public void setMethodName(int i,String s)
    {
        this.methodName[i] = s;
    }

    public int getSize(){
        return totalGrade.length;
    }
    public int getTotalGrade(int i){
        return totalGrade[i];
    }
    public void setGrade(int i, int g)
    {
        this.grade[i] = g;
    }
    public int getGrade(int i)
    {
        return this.grade[i];
    }
    public void setComment(int i, String comment)
    {
        this.comment[i] = comment;
    }
    public String getComment(int i)
    {
        return this.comment[i];
    }
    public void setTotalGrade(int i, int grade)
    {
        this.totalGrade[i] = grade;
    }


}
class Parameters
{
    private final String methodname;
    private final int returns;
    private final int breaks;
    private final int continues;
    private final int weight;
    public Parameters(String[] key)
    {
        methodname = key[0];
        breaks = Integer.parseInt(key[1]);
        continues = Integer.parseInt(key[2]);
        returns = Integer.parseInt(key[3]);
        weight = Integer.parseInt(key[4]);
    }

    public String getMethodname() {
        return methodname;
    }

    public int getReturns() {
        return returns;
    }

    public int getBreaks() {
        return breaks;
    }

    public int getContinues() {
        return continues;
    }

    public int getWeight() {
        return weight;
    }
    public String toString()
    {
        return String.format("%s|%d|%d|%d|%d",this.methodname,this.breaks,this.continues,this.returns,this.weight);
    }
}
