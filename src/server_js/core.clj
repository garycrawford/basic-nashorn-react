(ns server-js.core
  (import (javax.script ScriptEngineManager)))

(def nashorn (.getEngineByName (ScriptEngineManager.) "nashorn"))

(def react (slurp (clojure.java.io/resource "js/react-0.10.0.js")))

(defn main 
  ""
  []
  (.eval nashorn "var global = this") ;; React expects 'window' or 'global' to be set
  (.eval nashorn react)               ;; Loads react.js into Nashorn

  (.eval nashorn "var MyComponent = React.createClass({
           render: function () {
                     return React.DOM.h1(null, 'Hi, ' + this.props.msg)
                   }
                 })") ;; creates a basic react component

  (.eval nashorn "React.renderComponentToString(MyComponent({msg: 'World!'}))")) ;; renders the react component
