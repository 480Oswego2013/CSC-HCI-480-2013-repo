function init() {
  // Instanciate sigma.js and customize rendering :
  var sigInst = sigma.init(document.getElementById('sigma-example')).drawingProperties({
    edgeColor: '000',
    labelThreshold: 12,
    //defaultEdgeType: 'curve',
    defaultLabelColor: '#fff'
  }).graphProperties({
    minNodeSize: .5,
    maxNodeSize: 3,
    minEdgeSize: 2,
    maxEdgeSize: 3
  }).mouseProperties({
    maxRatio: 32
  });
  
  var tempI = 0;
  var leafCount = 0;
  var nodeScale = 0.01;
  var leafID;
  var nodeID;

  function traverse(parent, node, level, index){

    //Print node to console (leaf)
    if (node.hasOwnProperty('label')) {
      console.log(node.label);
    }
    else {  //Print node to console (internal)
      console.log(node.field + " " + node.condition + " " + node.value);
    }

    //Create node (leaf)
    if (node.hasOwnProperty('label')){
      //Construct ID, increment leafCount
      leafID = "l-"+node.label+leafCount+"-l";
      leafCount++;
      //Add node
      sigInst.addNode(leafID, {
        label: node.label,
        x: node.indexDispX*nodeScale,
        y: node.levelDispY*nodeScale,
        color: '#9de24f'
      });
      console.log(leafID);
    } 
    //Create node (internal)
    else {
      //Construct ID
      nodeID = "n-"+node.indexDispX+"-"+node.levelDispY+"-n";
      //Add node
      sigInst.addNode( nodeID, {
        label: node.field + " " + node.condition + " " + node.value,
        x: node.indexDispX*nodeScale,
        y: node.levelDispY*nodeScale,
        color: '#5ac3b6'
      });
      console.log(nodeID);
    }

    //Create edge 
    if(parent != null){
      if(node.hasOwnProperty('label')){
        var parentID = "n-"+parent.indexDispX+"-"+parent.levelDispY+"-n";
        var edgeID = parentID + "-e-" + leafID;
        console.log("LEdge: "+edgeID);
        sigInst.addEdge(edgeID,parentID,leafID);
      }
      else{ 
        var parentID = "n-"+parent.indexDispX+"-"+parent.levelDispY+"-n";
        var edgeID = parentID+ "-e-" + nodeID;
        console.log("NEdge: "+edgeID);
        sigInst.addEdge(edgeID,parentID,nodeID);
      }
    }

    if (typeof node.children != 'undefined') {
      for (var i = 0; i < node.children.length; i++) {
        if(i == 0){
  
          traverse(node,node.children[i],level+1,index-1);
        }
        else if (i == 1){
          traverse(node,node.children[i],level+1,index+1);
        }
      }
    }
  }

  function setLevelIndex(node){
    tempI = 0;
    setLI(node,0);
  }

  function setLI(node, level){
    if(node != null){

      node.levelDispY = level;

      if(node.hasOwnProperty('children') && node.children[0] != null){

        level++;

        setLI(node.children[0],level);
      }

      node.indexDispX = tempI;

     tempI++;
     
     if(node.hasOwnProperty('children') && node.children[1] != null){
        setLI(node.children[1],level);
     } 
    }
  }


  // var tree = {field:"petal_len",condition:"<=",value:2.45,
  //   children:[{label:"Iris-setosa"},{field:"petal_wid",condition:"<=",value:1.6500001,
  //     children:[{field:"sepal_len",condition:"<=",value:5.95,
  //       children:[{label:"Iris-virginica"},{field:"petal_wid",condition:"<=",value:1.8499999,
  //         children:[{field:"petal_len",condition:"<=",value:4.55,
  //           children:[{label:"Iris-virginica"},{label:"Iris-versicolor"}]},

  //   {label:"Iris-virginica"}]}]},{field:"petal_len",condition:"<=",value:4.95,
  //     children:[{label:"Iris-versicolor"},{field:"sepal_len",condition:"<=",value:6.05,
  //       children:[{label:"Iris-virginica"},{label:"Iris-versicolor"}]}]}]}]};


  var tree = jQuery.parseJSON('{"field":"displacement (cc)","condition":"<=","value":303.0,"children":[{"field":"power (hp)","condition":"<=","value":76.5,"children":[{"field":"economy (mpg)","condition":"<=","value":29.25,"children":[{"field":"displacement (cc)","condition":"<=","value":115.5,"children":[{"field":"power (hp)","condition":"<=","value":69.5,"children":[{"field":"weight (lb)","condition":"<=","value":2128.0,"children":[{"field":"name","condition":"<=","value":284.5,"children":[{"field":"weight (lb)","condition":"<=","value":1835.5,"children":[{"label":"Class 3"},{"label":"Class 0"}]},{"label":"Class 4"}]},{"field":"displacement (cc)","condition":"<=","value":97.75,"children":[{"label":"Class 6"},{"label":"Class 2"}]}]},{"field":"name","condition":"<=","value":266.5,"children":[{"field":"power (hp)","condition":"<=","value":70.5,"children":[{"label":"Class 6"},{"field":"name","condition":"<=","value":285.5,"children":[{"label":"Class 5"},{"field":"0-60 mph (s)","condition":"<=","value":16.3,"children":[{"label":"Class 5"},{"label":"Class 6"}]}]}]},{"label":"Class 1"}]}]},{"field":"0-60 mph (s)","condition":"<=","value":18.150002,"children":[{"field":"name","condition":"<=","value":149.5,"children":[{"label":"Class 4"},{"label":"Class 2"}]},{"field":"name","condition":"<=","value":191.5,"children":[{"field":"weight (lb)","condition":"<=","value":2403.0,"children":[{"label":"Class 3"},{"label":"Class 1"}]},{"label":"Class 5"}]}]}]},{"field":"displacement (cc)","condition":"<=","value":84.0,"children":[{"field":"power (hp)","condition":"<=","value":61.5,"children":[{"field":"name","condition":"<=","value":268.5,"children":[{"label":"Class 4"},{"field":"weight (lb)","condition":"<=","value":1987.5,"children":[{"label":"Class 8"},{"label":"Class 4"}]}]},{"field":"name","condition":"<=","value":202.5,"children":[{"field":"power (hp)","condition":"<=","value":67.5,"children":[{"label":"Class 1"},{"field":"0-60 mph (s)","condition":"<=","value":19.1,"children":[{"label":"Class 4"},{"label":"Class 1"}]}]},{"label":"Class 4"}]}]},{"field":"name","condition":"<=","value":166.5,"children":[{"field":"power (hp)","condition":"<=","value":65.5,"children":[{"field":"power (hp)","condition":"<=","value":63.5,"children":[{"label":"Class 7"},{"field":"name","condition":"<=","value":127.5,"children":[{"field":"economy (mpg)","condition":"<=","value":35.05,"children":[{"label":"Class 9"},{"label":"Class 10"}]},{"label":"Class 11"}]}]},{"field":"power (hp)","condition":"<=","value":75.5,"children":[{"field":"displacement (cc)","condition":"<=","value":97.75,"children":[{"field":"0-60 mph (s)","condition":"<=","value":16.95,"children":[{"label":"Class 7"},{"label":"Class 6"}]},{"field":"weight (lb)","condition":"<=","value":2109.0,"children":[{"field":"weight (lb)","condition":"<=","value":2040.0,"children":[{"label":"Class 7"},{"label":"Class 8"}]},{"field":"displacement (cc)","condition":"<=","value":106.0,"children":[{"label":"Class 10"},{"field":"0-60 mph (s)","condition":"<=","value":16.3,"children":[{"label":"Class 8"},{"field":"name","condition":"<=","value":111.5,"children":[{"label":"Class 10"},{"label":"Class 8"}]}]}]}]}]},{"label":"Class 1"}]}]},{"field":"power (hp)","condition":"<=","value":60.5,"children":[{"field":"name","condition":"<=","value":271.5,"children":[{"field":"economy (mpg)","condition":"<=","value":44.449997,"children":[{"label":"Class 1"},{"field":"0-60 mph (s)","condition":"<=","value":24.150002,"children":[{"label":"Class 10"},{"label":"Class 12"}]}]},{"field":"0-60 mph (s)","condition":"<=","value":17.349998,"children":[{"label":"Class 5"},{"label":"Class 8"}]}]},{"field":"economy (mpg)","condition":"<=","value":39.05,"children":[{"label":"Class 10"},{"field":"power (hp)","condition":"<=","value":70.5,"children":[{"field":"name","condition":"<=","value":288.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":15.05,"children":[{"label":"Class 12"},{"label":"Class 8"}]},{"field":"weight (lb)","condition":"<=","value":2342.5,"children":[{"field":"displacement (cc)","condition":"<=","value":107.5,"children":[{"label":"Class 10"},{"label":"Class 11"}]},{"label":"Class 11"}]}]},{"field":"0-60 mph (s)","condition":"<=","value":15.25,"children":[{"field":"weight (lb)","condition":"<=","value":2207.5,"children":[{"field":"power (hp)","condition":"<=","value":68.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":15.05,"children":[{"label":"Class 9"},{"label":"Class 12"}]},{"label":"Class 9"}]},{"label":"Class 11"}]},{"field":"displacement (cc)","condition":"<=","value":107.5,"children":[{"label":"Class 12"},{"field":"0-60 mph (s)","condition":"<=","value":16.150002,"children":[{"field":"0-60 mph (s)","condition":"<=","value":17.95,"children":[{"label":"Class 11"},{"label":"Class 10"}]},{"field":"name","condition":"<=","value":277.5,"children":[{"label":"Class 10"},{"label":"Class 12"}]}]}]}]}]}]}]}]}]}]},{"field":"economy (mpg)","condition":"<=","value":21.8,"children":[{"field":"weight (lb)","condition":"<=","value":3028.0,"children":[{"field":"cylinders","condition":"<=","value":4.5,"children":[{"field":"name","condition":"<=","value":257.5,"children":[{"field":"weight (lb)","condition":"<=","value":2315.0,"children":[{"label":"Class 3"},{"label":"Class 2"}]},{"field":"power (hp)","condition":"<=","value":94.5,"children":[{"label":"Class 3"},{"field":"name","condition":"<=","value":294.5,"children":[{"label":"Class 8"},{"label":"Class 3"}]}]}]},{"field":"displacement (cc)","condition":"<=","value":228.0,"children":[{"field":"power (hp)","condition":"<=","value":102.5,"children":[{"field":"displacement (cc)","condition":"<=","value":241.0,"children":[{"field":"0-60 mph (s)","condition":"<=","value":15.65,"children":[{"field":"economy (mpg)","condition":"<=","value":19.599998,"children":[{"field":"economy (mpg)","condition":"<=","value":18.05,"children":[{"label":"Class 3"},{"label":"Class 4"}]},{"label":"Class 5"}]},{"label":"Class 1"}]},{"label":"Class 3"}]},{"label":"Class 1"}]},{"field":"power (hp)","condition":"<=","value":95.5,"children":[{"label":"Class 0"},{"field":"name","condition":"<=","value":179.5,"children":[{"label":"Class 3"},{"label":"Class 4"}]}]}]}]},{"field":"weight (lb)","condition":"<=","value":3727.5,"children":[{"field":"power (hp)","condition":"<=","value":106.0,"children":[{"field":"power (hp)","condition":"<=","value":101.0,"children":[{"field":"displacement (cc)","condition":"<=","value":228.0,"children":[{"field":"economy (mpg)","condition":"<=","value":20.1,"children":[{"field":"0-60 mph (s)","condition":"<=","value":17.650002,"children":[{"label":"Class 6"},{"field":"displacement (cc)","condition":"<=","value":198.5,"children":[{"label":"Class 5"},{"label":"Class 4"}]}]},{"label":"Class 8"}]},{"field":"0-60 mph (s)","condition":"<=","value":16.95,"children":[{"field":"0-60 mph (s)","condition":"<=","value":17.25,"children":[{"label":"Class 6"},{"field":"power (hp)","condition":"<=","value":96.5,"children":[{"label":"Class 8"},{"label":"Class 4"}]}]},{"label":"Class 1"}]}]},{"field":"name","condition":"<=","value":232.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":16.45,"children":[{"label":"Class 7"},{"field":"displacement (cc)","condition":"<=","value":231.5,"children":[{"label":"Class 5"},{"label":"Class 8"}]}]},{"field":"weight (lb)","condition":"<=","value":3566.5,"children":[{"label":"Class 4"},{"label":"Class 1"}]}]}]},{"field":"cylinders","condition":"<=","value":7.0,"children":[{"field":"displacement (cc)","condition":"<=","value":231.5,"children":[{"field":"economy (mpg)","condition":"<=","value":20.55,"children":[{"label":"Class 8"},{"label":"Class 9"}]},{"field":"weight (lb)","condition":"<=","value":3631.0,"children":[{"field":"displacement (cc)","condition":"<=","value":254.0,"children":[{"label":"Class 4"},{"label":"Class 6"}]},{"field":"weight (lb)","condition":"<=","value":3512.0,"children":[{"label":"Class 7"},{"label":"Class 8"}]}]}]},{"field":"power (hp)","condition":"<=","value":138.5,"children":[{"label":"Class 8"},{"field":"name","condition":"<=","value":149.5,"children":[{"label":"Class 5"},{"field":"displacement (cc)","condition":"<=","value":264.5,"children":[{"label":"Class 5"},{"label":"Class 9"}]}]}]}]}]},{"field":"power (hp)","condition":"<=","value":132.5,"children":[{"field":"economy (mpg)","condition":"<=","value":13.5,"children":[{"label":"Class 2"},{"field":"power (hp)","condition":"<=","value":137.5,"children":[{"label":"Class 3"},{"label":"Class 4"}]}]},{"field":"name","condition":"<=","value":39.5,"children":[{"field":"power (hp)","condition":"<=","value":101.0,"children":[{"label":"Class 7"},{"label":"Class 4"}]},{"label":"Class 5"}]}]}]}]},{"field":"0-60 mph (s)","condition":"<=","value":16.05,"children":[{"field":"displacement (cc)","condition":"<=","value":110.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":19.75,"children":[{"field":"economy (mpg)","condition":"<=","value":23.650002,"children":[{"field":"power (hp)","condition":"<=","value":88.5,"children":[{"field":"name","condition":"<=","value":149.5,"children":[{"label":"Class 9"},{"label":"Class 5"}]},{"label":"Class 6"}]},{"field":"power (hp)","condition":"<=","value":81.5,"children":[{"field":"power (hp)","condition":"<=","value":80.5,"children":[{"field":"weight (lb)","condition":"<=","value":2533.5,"children":[{"label":"Class 4"},{"label":"Class 12"}]},{"label":"Class 6"}]},{"label":"Class 12"}]}]},{"field":"power (hp)","condition":"<=","value":86.5,"children":[{"label":"Class 9"},{"field":"0-60 mph (s)","condition":"<=","value":20.25,"children":[{"label":"Class 9"},{"label":"Class 11"}]}]}]},{"field":"economy (mpg)","condition":"<=","value":23.650002,"children":[{"field":"economy (mpg)","condition":"<=","value":27.7,"children":[{"label":"Class 2"},{"field":"power (hp)","condition":"<=","value":80.5,"children":[{"label":"Class 0"},{"label":"Class 2"}]}]},{"label":"Class 3"}]}]},{"field":"economy (mpg)","condition":"<=","value":26.7,"children":[{"field":"power (hp)","condition":"<=","value":97.5,"children":[{"field":"power (hp)","condition":"<=","value":95.5,"children":[{"field":"name","condition":"<=","value":272.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":14.95,"children":[{"label":"Class 4"},{"label":"Class 7"}]},{"field":"weight (lb)","condition":"<=","value":2670.5,"children":[{"label":"Class 2"},{"label":"Class 5"}]}]},{"field":"displacement (cc)","condition":"<=","value":134.5,"children":[{"field":"economy (mpg)","condition":"<=","value":25.05,"children":[{"field":"0-60 mph (s)","condition":"<=","value":14.15,"children":[{"label":"Class 0"},{"label":"Class 1"}]},{"field":"name","condition":"<=","value":208.5,"children":[{"label":"Class 4"},{"label":"Class 3"}]}]},{"field":"0-60 mph (s)","condition":"<=","value":15.75,"children":[{"field":"cylinders","condition":"<=","value":5.5,"children":[{"field":"weight (lb)","condition":"<=","value":2586.0,"children":[{"label":"Class 12"},{"label":"Class 11"}]},{"label":"Class 0"}]},{"field":"economy (mpg)","condition":"<=","value":23.1,"children":[{"label":"Class 7"},{"label":"Class 3"}]}]}]}]},{"field":"cylinders","condition":"<=","value":4.5,"children":[{"field":"displacement (cc)","condition":"<=","value":114.5,"children":[{"label":"Class 10"},{"label":"Class 5"}]},{"field":"economy (mpg)","condition":"<=","value":22.15,"children":[{"field":"name","condition":"<=","value":143.5,"children":[{"label":"Class 6"},{"label":"Class 12"}]},{"label":"Class 11"}]}]}]},{"field":"power (hp)","condition":"<=","value":83.5,"children":[{"field":"economy (mpg)","condition":"<=","value":31.7,"children":[{"field":"power (hp)","condition":"<=","value":90.5,"children":[{"field":"name","condition":"<=","value":146.5,"children":[{"label":"Class 1"},{"field":"displacement (cc)","condition":"<=","value":137.5,"children":[{"label":"Class 12"},{"label":"Class 11"}]}]},{"field":"power (hp)","condition":"<=","value":97.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":13.55,"children":[{"label":"Class 10"},{"label":"Class 9"}]},{"label":"Class 8"}]}]},{"field":"power (hp)","condition":"<=","value":99.0,"children":[{"field":"power (hp)","condition":"<=","value":88.5,"children":[{"field":"weight (lb)","condition":"<=","value":2489.5,"children":[{"label":"Class 12"},{"label":"Class 10"}]},{"field":"0-60 mph (s)","condition":"<=","value":13.75,"children":[{"label":"Class 12"},{"label":"Class 9"}]}]},{"label":"Class 11"}]}]},{"field":"weight (lb)","condition":"<=","value":1938.5,"children":[{"label":"Class 9"},{"field":"economy (mpg)","condition":"<=","value":28.05,"children":[{"label":"Class 7"},{"label":"Class 2"}]}]}]}]}]}]}]},{"field":"power (hp)","condition":"<=","value":148.5,"children":[{"field":"0-60 mph (s)","condition":"<=","value":11.45,"children":[{"field":"economy (mpg)","condition":"<=","value":15.25,"children":[{"field":"0-60 mph (s)","condition":"<=","value":13.1,"children":[{"field":"economy (mpg)","condition":"<=","value":14.75,"children":[{"field":"power (hp)","condition":"<=","value":162.5,"children":[{"field":"weight (lb)","condition":"<=","value":3779.0,"children":[{"label":"Class 3"},{"label":"Class 2"}]},{"label":"Class 0"}]},{"field":"name","condition":"<=","value":51.5,"children":[{"label":"Class 3"},{"field":"economy (mpg)","condition":"<=","value":12.5,"children":[{"field":"name","condition":"<=","value":123.5,"children":[{"label":"Class 3"},{"label":"Class 1"}]},{"field":"name","condition":"<=","value":242.5,"children":[{"label":"Class 1"},{"field":"weight (lb)","condition":"<=","value":4733.5,"children":[{"label":"Class 1"},{"field":"displacement (cc)","condition":"<=","value":350.5,"children":[{"field":"weight (lb)","condition":"<=","value":4212.0,"children":[{"label":"Class 1"},{"label":"Class 2"}]},{"field":"name","condition":"<=","value":232.5,"children":[{"label":"Class 2"},{"field":"name","condition":"<=","value":144.5,"children":[{"label":"Class 3"},{"label":"Class 2"}]}]}]}]}]}]}]}]},{"field":"0-60 mph (s)","condition":"<=","value":13.299999,"children":[{"field":"power (hp)","condition":"<=","value":162.5,"children":[{"label":"Class 0"},{"field":"0-60 mph (s)","condition":"<=","value":14.45,"children":[{"label":"Class 3"},{"label":"Class 2"}]}]},{"label":"Class 6"}]}]},{"field":"weight (lb)","condition":"<=","value":4178.0,"children":[{"field":"weight (lb)","condition":"<=","value":4381.0,"children":[{"label":"Class 5"},{"field":"displacement (cc)","condition":"<=","value":329.0,"children":[{"field":"power (hp)","condition":"<=","value":159.0,"children":[{"label":"Class 9"},{"label":"Class 6"}]},{"label":"Class 6"}]}]},{"field":"weight (lb)","condition":"<=","value":3923.5,"children":[{"field":"power (hp)","condition":"<=","value":151.0,"children":[{"label":"Class 0"},{"label":"Class 9"}]},{"field":"weight (lb)","condition":"<=","value":3638.5,"children":[{"label":"Class 0"},{"label":"Class 2"}]}]}]}]},{"field":"name","condition":"<=","value":130.5,"children":[{"label":"Class 0"},{"field":"economy (mpg)","condition":"<=","value":13.5,"children":[{"field":"power (hp)","condition":"<=","value":172.5,"children":[{"label":"Class 0"},{"label":"Class 7"}]},{"label":"Class 3"}]}]}]},{"field":"displacement (cc)","condition":"<=","value":329.0,"children":[{"field":"0-60 mph (s)","condition":"<=","value":13.1,"children":[{"field":"displacement (cc)","condition":"<=","value":350.5,"children":[{"field":"power (hp)","condition":"<=","value":143.5,"children":[{"label":"Class 5"},{"label":"Class 9"}]},{"field":"0-60 mph (s)","condition":"<=","value":14.05,"children":[{"label":"Class 9"},{"field":"weight (lb)","condition":"<=","value":4646.0,"children":[{"label":"Class 9"},{"label":"Class 5"}]}]}]},{"field":"name","condition":"<=","value":62.5,"children":[{"label":"Class 6"},{"label":"Class 3"}]}]},{"field":"power (hp)","condition":"<=","value":136.0,"children":[{"field":"power (hp)","condition":"<=","value":121.0,"children":[{"field":"name","condition":"<=","value":111.5,"children":[{"field":"weight (lb)","condition":"<=","value":3820.5,"children":[{"label":"Class 9"},{"label":"Class 0"}]},{"label":"Class 9"}]},{"label":"Class 6"}]},{"field":"weight (lb)","condition":"<=","value":4199.5,"children":[{"label":"Class 6"},{"field":"power (hp)","condition":"<=","value":141.0,"children":[{"label":"Class 8"},{"field":"weight (lb)","condition":"<=","value":3845.0,"children":[{"label":"Class 7"},{"label":"Class 8"}]}]}]}]}]}]}]}');

  setLevelIndex(tree);
  traverse(null,tree,0,0); 

  // Draw the graph :
  sigInst.draw();
}


if (document.addEventListener) {
  document.addEventListener("DOMContentLoaded", init, false);
} else {
  window.onload = init;
}
