/**
 * jsdp: A Java Stochastic Dynamic Programming Library
 * 
 * MIT License
 * 
 * Copyright (c) 2016 Roberto Rossi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jsdp.app.inventory.univariate;

import jsdp.sdp.Action;
import jsdp.sdp.ActionIterator;
import jsdp.sdp.impl.univariate.ActionImpl;
import jsdp.sdp.impl.univariate.StateImpl;

public class ActionIteratorImpl extends ActionIterator {

   StateImpl state;
   ActionImpl currentAction;
   double actionPointer;
   
   public ActionIteratorImpl(StateImpl state){
      this.state = state;
      this.actionPointer = state.getInitialState();
      currentAction = new ActionImpl(state, this.actionPointer - state.getInitialState());
   }
   
   @Override
   public boolean hasNext() {
      if(this.actionPointer > StateImpl.getMaxState())
         return false;
      else
         return true;
   }

   @Override
   public Action next() {
      ActionImpl action = this.currentAction;
      this.actionPointer += StateImpl.getStepSize();
      currentAction = new ActionImpl(state, this.actionPointer - state.getInitialState());
      return action;
   }
}