package jsdp.utilities.monitoring;

import jsdp.sdp.Recursion;

/**
 * Monitoring interface for forward recursion
 * 
 * @author Roberto Rossi
 *
 */
@SuppressWarnings("restriction")
public class MonitoringInterfaceForward extends MonitoringInterface{
   
   private static final long serialVersionUID = 1L;
   
   protected long reusedStates;
   
   public MonitoringInterfaceForward(Recursion recursion){
      recursion.setStateMonitoring(true);
      this.setTitle("jsdp statistics");
      this.text.setEditable(false);
      this.getContentPane().add(text);
      this.setSize(300, 120);
      this.setVisible(true);
   }
   
   /**
    * Set states status
    * 
    * @param generatedStates number of generated states
    * @param reusedStates number of reused 
    */
   public void setStates(long generatedStates, long reusedStates) {
      this.generatedStates = generatedStates;
      this.reusedStates = reusedStates;
   }
   
   @Override
   public void run() {
      this.cpuAfter = this.osMBean.getProcessCpuTime();
      this.nanoAfter = System.nanoTime();
      while(!terminate){
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
         this.cpuAfter = this.osMBean.getProcessCpuTime();
         this.nanoAfter = System.nanoTime();
            
         setText("Time: " + this.getTime() +"\n"
               + "CPU: "  + this.getPercentCPU() +"%" +" ("+Runtime.getRuntime().availableProcessors()+" cores)\n"
               + "States processed per second: "+ getProcessedStatesPerSecond() +"\n"
               + "Generated states: " + this.getGeneratedStates() +"\n"
               + "Reused states: " + this.getReusedStates());
      }
   }
   
   /**
    * Get number of states that have been reused in the forward recursion process. 
    * In forward recursion if a state has been already visited, the optimal cost/action
    * do not need to be recomputed.
    * 
    * @return number of states that have been reused
    * @see <a href="https://en.wikipedia.org/wiki/Memoization">Memoization</a>
    */
   public long getReusedStates(){
      return this.reusedStates;
   }
   
   @Override
   public double getProcessedStatesPerSecond(){
      return (int) Math.ceil((generatedStates+reusedStates)/((this.nanoAfter-this.nanoBefore)*Math.pow(10, -9)));
   }
   
   @Override
   public String toString(){
      return "Time: " + this.getTime() +"\n"
            + "CPU: "  + this.getPercentCPU() +"%" +" ("+Runtime.getRuntime().availableProcessors()+" cores)\n"
            + "States processed per second: "+ getProcessedStatesPerSecond() +"\n"
            + "Generated states: " + this.getGeneratedStates() +"\n"
            + "Reused states: " + this.getReusedStates();
   }
}
