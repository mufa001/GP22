#!/usr/bin/tclsh

set TEMPLATE "template.tex"
set TMP "tmp"

proc createJavaSnip {command {outFileName ""}} {

   set complex 0

   set menuLabel ""
   set editorText ""

   regexp {^\\([a-zA-Z]+)} $command -> menuLabel
   set editorText $menuLabel

   if {![string length $outFileName]} {
      set outFileName $menuLabel.png
   }

   for {set i 0} {$i < [regexp -all {\[.*?\]}  $command]} {incr i} {
         set complex 1
         append menuLabel {[ ]}
         append editorText {[]}
   }

   for {set i 0} {$i < [regexp -all "{.*?}" $command]} {incr i} {
         set complex 1
         append menuLabel "{ }"
         append editorText "{}"
   }

   if {$complex} {
      return "add(new MathSymbolMenuItemC(editorPane, \"\\\\$menuLabel\", \"\\\\$editorText\", \"$outFileName\"));"
   } else {
      return "add(new MathSymbolMenuItemC(editorPane, \"\\\\$menuLabel\", \"$outFileName\"));"
   }
}


proc createIcon {command {outFileName ""}} {
   global TEMPLATE TMP

   set templateFP [open $TEMPLATE r]
   set tmpFP [open $TMP.tex w]

   while {[gets $templateFP line] >= 0} {
      regsub "@@REPLACE@@" $line $command line
      puts $tmpFP $line
   }

   close $templateFP
   close $tmpFP


   if {![string length $outFileName]} {
      regexp {\\([a-zA-Z]+).*} $command -> outFileName
      set outFileName $outFileName.png
   }

   exec latex $TMP.tex
   exec dvipng -q -T tight -x 1200 -z 9 -bg Transparent -o "$outFileName" "$TMP.dvi"

   eval file delete [glob tmp\.*]

   return $command
}



proc LatexGreekUppercase {} {
   puts ""
   puts "--- LatexGreekUppercaseMenuC ---"

   puts [createJavaSnip [createIcon "\\Gamma" "caseGamma.png"] "caseGamma.png"]
   puts [createJavaSnip [createIcon "\\Delta" "caseDelta.png"] "caseDelta.png"]
   puts [createJavaSnip [createIcon "\\Lambda" "caseLambda.png"] "caseLambda.png"]
   puts [createJavaSnip [createIcon "\\Phi" "casePhi.png"] "casePhi.png"]
   puts [createJavaSnip [createIcon "\\Pi" "casePi.png"] "casePi.png"]
   puts [createJavaSnip [createIcon "\\Psi" "casePsi.png"] "casePsi.png"]
   puts [createJavaSnip [createIcon "\\Sigma" "caseSigma.png"] "caseSigma.png"]
   puts [createJavaSnip [createIcon "\\Theta" "caseTheta.png"] "caseTheta.png"]
   puts [createJavaSnip [createIcon "\\Upsilon" "caseUpsilon.png"] "caseUpsilon.png"]
   puts [createJavaSnip [createIcon "\\Xi" "caseXi.png"] "caseXi.png"]
   puts [createJavaSnip [createIcon "\\Omega" "caseOmega.png"] "caseOmega.png"]
}


proc LatexGreekLowercase {} {
   puts ""
   puts "--- Greek Lowercase ---"

   puts [createJavaSnip [createIcon "\\alpha"]]
   puts [createJavaSnip [createIcon "\\beta"]]
   puts [createJavaSnip [createIcon "\\gamma"]]
   puts [createJavaSnip [createIcon "\\delta"]]
   puts [createJavaSnip [createIcon "\\epsilon"]]
   puts [createJavaSnip [createIcon "\\zeta"]]
   puts [createJavaSnip [createIcon "\\eta"]]
   puts [createJavaSnip [createIcon "\\theta"]]
   puts [createJavaSnip [createIcon "\\iota"]]
   puts [createJavaSnip [createIcon "\\kappa"]]
   puts [createJavaSnip [createIcon "\\lambda"]]
   puts [createJavaSnip [createIcon "\\mu"]]
   puts [createJavaSnip [createIcon "\\nu"]]
   puts [createJavaSnip [createIcon "\\xi"]]
   puts [createJavaSnip [createIcon "\\pi"]]
   puts [createJavaSnip [createIcon "\\rho"]]
   puts [createJavaSnip [createIcon "\\sigma"]]
   puts [createJavaSnip [createIcon "\\tau"]]
   puts [createJavaSnip [createIcon "\\upsilon"]]
   puts [createJavaSnip [createIcon "\\phi"]]
   puts [createJavaSnip [createIcon "\\chi"]]
   puts [createJavaSnip [createIcon "\\psi"]]
   puts [createJavaSnip [createIcon "\\omega"]]
   puts [createJavaSnip [createIcon "\\varepsilon"]]
   puts [createJavaSnip [createIcon "\\vartheta"]]
   puts [createJavaSnip [createIcon "\\varpi"]]
   puts [createJavaSnip [createIcon "\\varrho"]]
   puts [createJavaSnip [createIcon "\\varsigma"]]
   puts [createJavaSnip [createIcon "\\varphi"]]
}


proc LatexBinaryOperator {} {
   puts ""
   puts "--- Binary Op ---"

   puts [createJavaSnip [createIcon "\\pm"]]
   puts [createJavaSnip [createIcon "\\mp"]]
   puts [createJavaSnip [createIcon "\\times"]]
   puts [createJavaSnip [createIcon "\\div"]]
   puts [createJavaSnip [createIcon "\\ast"]]
   puts [createJavaSnip [createIcon "\\star"]]
   puts [createJavaSnip [createIcon "\\circ"]]
   puts [createJavaSnip [createIcon "\\bullet"]]
   puts [createJavaSnip [createIcon "\\cdot"]]
   puts [createJavaSnip [createIcon "\\cap"]]
   puts [createJavaSnip [createIcon "\\cup"]]
   puts [createJavaSnip [createIcon "\\uplus"]]
   puts [createJavaSnip [createIcon "\\sqcap"]]
   puts [createJavaSnip [createIcon "\\sqcup"]]
   puts [createJavaSnip [createIcon "\\vee"]]
   puts [createJavaSnip [createIcon "\\wedge"]]
   puts [createJavaSnip [createIcon "\\setminus"]]
   puts [createJavaSnip [createIcon "\\wr"]]
   puts [createJavaSnip [createIcon "\\diamond"]]
   puts [createJavaSnip [createIcon "\\bigtriangleup"]]
   puts [createJavaSnip [createIcon "\\bigtriangledown"]]
   puts [createJavaSnip [createIcon "\\triangleleft"]]
   puts [createJavaSnip [createIcon "\\triangleright"]]
   puts [createJavaSnip [createIcon "\\lhd"]]
   puts [createJavaSnip [createIcon "\\rhd"]]
   puts [createJavaSnip [createIcon "\\unlhd"]]
   puts [createJavaSnip [createIcon "\\unrhd"]]
   puts [createJavaSnip [createIcon "\\oplus"]]
   puts [createJavaSnip [createIcon "\\ominus"]]
   puts [createJavaSnip [createIcon "\\otimes"]]
   puts [createJavaSnip [createIcon "\\oslash"]]
   puts [createJavaSnip [createIcon "\\odot"]]
   puts [createJavaSnip [createIcon "\\bigcirc"]]
   puts [createJavaSnip [createIcon "\\dagger"]]
   puts [createJavaSnip [createIcon "\\ddagger"]]
#puts [createJavaSnip [createIcon "\\amalg"]]
}


proc LatexRelation {} {
   puts ""
   puts "--- Relation ---"

   puts [createJavaSnip [createIcon "\\leq"]]
   puts [createJavaSnip [createIcon "\\geq"]]
#puts [createJavaSnip [createIcon "\\qed"]]
   puts [createJavaSnip [createIcon "\\equiv"]]
   puts [createJavaSnip [createIcon "\\models"]]
   puts [createJavaSnip [createIcon "\\prec"]]
   puts [createJavaSnip [createIcon "\\succ"]]
   puts [createJavaSnip [createIcon "\\sim"]]
   puts [createJavaSnip [createIcon "\\perp"]]
   puts [createJavaSnip [createIcon "\\preceq"]]
   puts [createJavaSnip [createIcon "\\succeq"]]
   puts [createJavaSnip [createIcon "\\simeq"]]
   puts [createJavaSnip [createIcon "\\mid"]]
   puts [createJavaSnip [createIcon "\\ll"]]
   puts [createJavaSnip [createIcon "\\gg"]]
   puts [createJavaSnip [createIcon "\\asymp"]]
   puts [createJavaSnip [createIcon "\\parallel"]]
   puts [createJavaSnip [createIcon "\\subset"]]
   puts [createJavaSnip [createIcon "\\supset"]]
   puts [createJavaSnip [createIcon "\\approx"]]
   puts [createJavaSnip [createIcon "\\bowtie"]]
   puts [createJavaSnip [createIcon "\\subseteq"]]
   puts [createJavaSnip [createIcon "\\supseteq"]]
   puts [createJavaSnip [createIcon "\\cong"]]
   puts [createJavaSnip [createIcon "\\Join"]]
   puts [createJavaSnip [createIcon "\\sqsubset"]]
   puts [createJavaSnip [createIcon "\\sqsupset"]]
   puts [createJavaSnip [createIcon "\\neq"]]
   puts [createJavaSnip [createIcon "\\smile"]]
   puts [createJavaSnip [createIcon "\\sqsubseteq"]]
   puts [createJavaSnip [createIcon "\\sqsupseteq"]]
   puts [createJavaSnip [createIcon "\\doteq"]]
   puts [createJavaSnip [createIcon "\\frown"]]
   puts [createJavaSnip [createIcon "\\in"]]
   puts [createJavaSnip [createIcon "\\ni"]]
   puts [createJavaSnip [createIcon "\\propto"]]
   puts [createJavaSnip [createIcon "\\vdash"]]
   puts [createJavaSnip [createIcon "\\dashv"]]
}


proc LatexArrows {} {
   puts ""
   puts "--- LatexArrowMenuC ---"

   puts [createJavaSnip [createIcon "\\leftarrow"]]
   puts [createJavaSnip [createIcon "\\Leftarrow" "caseLeftarrow.png"] "caseLeftarrow.png"]
   puts [createJavaSnip [createIcon "\\rightarrow" "caserightarrow.png"] "caserightarrow.png"]
   puts [createJavaSnip [createIcon "\\Rightarrow"]]
   puts [createJavaSnip [createIcon "\\leftrightarrow"]]
   puts [createJavaSnip [createIcon "\\Leftrightarrow" "caseLeftrightarrow.png"] "caseLeftrightarrow.png"]
   puts [createJavaSnip [createIcon "\\mapsto"]]
   puts [createJavaSnip [createIcon "\\hookleftarrow"]]
   puts [createJavaSnip [createIcon "\\leftharpoonup"]]
   puts [createJavaSnip [createIcon "\\leftharpoondown"]]
   puts [createJavaSnip [createIcon "\\longleftarrow"]]
   puts [createJavaSnip [createIcon "\\Longleftarrow" "caseLongleftarrow.png"] "caseLongleftarrow.png"]
   puts [createJavaSnip [createIcon "\\longrightarrow"]]
   puts [createJavaSnip [createIcon "\\Longrightarrow" "caseLongrightarrow.png"] "caseLongrightarrow.png"]
   puts [createJavaSnip [createIcon "\\longleftrightarrow"]]
   puts [createJavaSnip [createIcon "\\Longleftrightarrow" "caseLongleftrightarrow.png"] "caseLongleftrightarrow.png"]
   puts [createJavaSnip [createIcon "\\longmapsto"]]
   puts [createJavaSnip [createIcon "\\hookrightarrow"]]
   puts [createJavaSnip [createIcon "\\rightharpoonup"]]
   puts [createJavaSnip [createIcon "\\rightharpoondown"]]
   puts [createJavaSnip [createIcon "\\uparrow"]]
   puts [createJavaSnip [createIcon "\\Uparrow" "caseUparrow.png"] "caseUparrow.png"]
   puts [createJavaSnip [createIcon "\\downarrow"]]
   puts [createJavaSnip [createIcon "\\Downarrow" "caseDownarrow.png"] "caseDownarrow.png"]
   puts [createJavaSnip [createIcon "\\updownarrow"]]
   puts [createJavaSnip [createIcon "\\Updownarrow" "caseUpdownarrow.png"] "caseUpdownarrow.png"]
   puts [createJavaSnip [createIcon "\\nearrow"]]
   puts [createJavaSnip [createIcon "\\searrow"]]
   puts [createJavaSnip [createIcon "\\swarrow"]]
   puts [createJavaSnip [createIcon "\\nwarrow"]]
}

proc LatexPunctuation {} {
   puts ""
   puts "--- Punctuation ---"

   puts [createJavaSnip [createIcon "\\ldots"]]
   puts [createJavaSnip [createIcon "\\cdots"]]
   puts [createJavaSnip [createIcon "\\vdots"]]
   puts [createJavaSnip [createIcon "\\ddots"]]
   puts [createJavaSnip [createIcon "\\colon"]]
}


proc LatexMiscellaneous {} {
   puts ""
   puts "--- LatexMiscellaneousMenuC ---"
                                                      
   puts [createJavaSnip [createIcon "\\nabla"]]
   puts [createJavaSnip [createIcon "\\aleph"]]
   puts [createJavaSnip [createIcon "\\prime"]]
   puts [createJavaSnip [createIcon "\\forall"]]
   puts [createJavaSnip [createIcon "\\infty"]]
   puts [createJavaSnip [createIcon "\\hbar"]]
   puts [createJavaSnip [createIcon "\\emptyset"]]
   puts [createJavaSnip [createIcon "\\exists"]]
   puts [createJavaSnip [createIcon "\\surd"]]
   puts [createJavaSnip [createIcon "\\Box"]]
   puts [createJavaSnip [createIcon "\\triangle"]]
   puts [createJavaSnip [createIcon "\\Diamond" "caseDiamond.png"] "caseDiamond.png"]
   puts [createJavaSnip [createIcon "\\imath"]]
#puts [createJavaSnip [createIcon "\\jmath"]]
   puts [createJavaSnip [createIcon "\\ell"]]
   puts [createJavaSnip [createIcon "\\neg"]]
   puts [createJavaSnip [createIcon "\\not"]]
   puts [createJavaSnip [createIcon "\\top"]]
   puts [createJavaSnip [createIcon "\\flat"]]
   puts [createJavaSnip [createIcon "\\natural"]]
   puts [createJavaSnip [createIcon "\\sharp"]]
   puts [createJavaSnip [createIcon "\\wp"]]
   puts [createJavaSnip [createIcon "\\bot"]]
   puts [createJavaSnip [createIcon "\\clubsuit"]]
   puts [createJavaSnip [createIcon "\\diamondsuit"]]
   puts [createJavaSnip [createIcon "\\heartsuit"]]
   puts [createJavaSnip [createIcon "\\spadesuit"]]
   puts [createJavaSnip [createIcon "\\mho"]]
   puts [createJavaSnip [createIcon "\\Re"]]
   puts [createJavaSnip [createIcon "\\Im"]]
   puts [createJavaSnip [createIcon "\\angle"]]
   puts [createJavaSnip [createIcon "\\partial"]]
}


proc LatexVariableSize {} {
   puts ""
   puts "--- Variable Size ---"
                                                      
   puts [createJavaSnip [createIcon "\\sum"]]
   puts [createJavaSnip [createIcon "\\prod"]]
#puts [createJavaSnip [createIcon "\\coprod"]]
   puts [createJavaSnip [createIcon "\\int"]]
   puts [createJavaSnip [createIcon "\\oint"]]
   puts [createJavaSnip [createIcon "\\bigcap"]]
   puts [createJavaSnip [createIcon "\\bigcup"]]
   puts [createJavaSnip [createIcon "\\bigsqcup"]]
   puts [createJavaSnip [createIcon "\\bigvee"]]
   puts [createJavaSnip [createIcon "\\bigwedge"]]
   puts [createJavaSnip [createIcon "\\bigodot"]]
   puts [createJavaSnip [createIcon "\\bigotimes"]]
   puts [createJavaSnip [createIcon "\\bigoplus"]]
   puts [createJavaSnip [createIcon "\\biguplus"]]
}


proc LatexLogLike {} {
   puts ""
   puts "--- Log-like ---"

   puts [createJavaSnip [createIcon "\\arccos"]]
   puts [createJavaSnip [createIcon "\\arcsin"]]
   puts [createJavaSnip [createIcon "\\arctan"]]
   puts [createJavaSnip [createIcon "\\arg"]]
   puts [createJavaSnip [createIcon "\\cos"]]
   puts [createJavaSnip [createIcon "\\cosh"]]
   puts [createJavaSnip [createIcon "\\cot"]]
   puts [createJavaSnip [createIcon "\\coth"]]
   puts [createJavaSnip [createIcon "\\csc"]]
   puts [createJavaSnip [createIcon "\\deg"]]
   puts [createJavaSnip [createIcon "\\det"]]
   puts [createJavaSnip [createIcon "\\dim"]]
   puts [createJavaSnip [createIcon "\\exp"]]
   puts [createJavaSnip [createIcon "\\gcd"]]
   puts [createJavaSnip [createIcon "\\hom"]]
   puts [createJavaSnip [createIcon "\\inf"]]
   puts [createJavaSnip [createIcon "\\ker"]]
   puts [createJavaSnip [createIcon "\\lg"]]
   puts [createJavaSnip [createIcon "\\lim"]]
   puts [createJavaSnip [createIcon "\\liminf"]]
   puts [createJavaSnip [createIcon "\\limsup"]]
   puts [createJavaSnip [createIcon "\\ln"]]
   puts [createJavaSnip [createIcon "\\log"]]
   puts [createJavaSnip [createIcon "\\max"]]
   puts [createJavaSnip [createIcon "\\min"]]
   puts [createJavaSnip [createIcon "\\Pr"]]
   puts [createJavaSnip [createIcon "\\sec"]]
   puts [createJavaSnip [createIcon "\\sin"]]
   puts [createJavaSnip [createIcon "\\sinh"]]
   puts [createJavaSnip [createIcon "\\sup"]]
   puts [createJavaSnip [createIcon "\\tan"]]
   puts [createJavaSnip [createIcon "\\tanh"]]
}


proc LatexDelimiter {} {
   puts ""
   puts "--- LatexDelimiterMenuC ---"

#puts [createJavaSnip [createIcon "\\{"]]
#puts [createJavaSnip [createIcon "\\}"]]
   puts [createJavaSnip [createIcon "\\lfloor"]]
   puts [createJavaSnip [createIcon "\\rfloor"]]
   puts [createJavaSnip [createIcon "\\lceil"]]
   puts [createJavaSnip [createIcon "\\rceil"]]
   puts [createJavaSnip [createIcon "\\langle"]]
   puts [createJavaSnip [createIcon "\\rangle"]]
   puts [createJavaSnip [createIcon "\\rmoustache"]]
   puts [createJavaSnip [createIcon "\\lmoustache"]]
   puts [createJavaSnip [createIcon "\\rgroup"]]
   puts [createJavaSnip [createIcon "\\lgroup"]]
   puts [createJavaSnip [createIcon "\\backslash"]]
#puts [createJavaSnip [createIcon "\\|"]]
   puts [createJavaSnip [createIcon "\\arrowvert"]]
   puts [createJavaSnip [createIcon "\\Arrowvert" "caseArrowvert.png"] "caseArrowvert.png"]
   puts [createJavaSnip [createIcon "\\bracevert"]]
}


proc LatexConstruct {} {
   puts "" 
   puts "--- Construct ---"

   puts [createJavaSnip [createIcon "\\widetilde{abc}"]]
   puts [createJavaSnip [createIcon "\\widehat{abc}"]]
   puts [createJavaSnip [createIcon "\\overleftarrow{abc}"]]
   puts [createJavaSnip [createIcon "\\overrightarrow{abc}"]]
   puts [createJavaSnip [createIcon "\\overline{abc}"]]
   puts [createJavaSnip [createIcon "\\underline{abc}"]]
   puts [createJavaSnip [createIcon "\\overbrace{abc}"]]
   puts [createJavaSnip [createIcon "\\underbrace{abc}"]]
   puts [createJavaSnip [createIcon "\\sqrt{abc}"]]
   puts [createJavaSnip [createIcon "\\sqrt\[n\]{abc}" "sqrtn.png"] "sqrtn.png"]
   puts [createJavaSnip [createIcon "\\frac{abc}{xyz}"]]
}


proc LatexAccent {} {
   puts "" 
   puts "--- Accent ---"

   puts [createJavaSnip [createIcon "\\hat{a}"]]
   puts [createJavaSnip [createIcon "\\acute{a}"]]
   puts [createJavaSnip [createIcon "\\bar{a}"]]
   puts [createJavaSnip [createIcon "\\dot{a}"]]
   puts [createJavaSnip [createIcon "\\breve{a}"]]
   puts [createJavaSnip [createIcon "\\check{a}"]]
   puts [createJavaSnip [createIcon "\\grave{a}"]]
   puts [createJavaSnip [createIcon "\\vec{a}"]]
   puts [createJavaSnip [createIcon "\\ddot{a}"]]
   puts [createJavaSnip [createIcon "\\tilde{a}"]]
}


proc AmsHebrew {} {
   puts "" 
   puts "--- AMS/Hebrew ---"
            
   puts [createJavaSnip [createIcon "\\digamma"]]
   puts [createJavaSnip [createIcon "\\varkappa"]]
   puts [createJavaSnip [createIcon "\\beth"]]
   puts [createJavaSnip [createIcon "\\daleth"]]
   puts [createJavaSnip [createIcon "\\gimel"]]
}


proc AmsArrow {} {
   puts "" 
   puts "--- AMS/Arrow ---"
            
   puts [createJavaSnip [createIcon "\\dashrightarrow"]]
   puts [createJavaSnip [createIcon "\\dashleftarrow"]]
   puts [createJavaSnip [createIcon "\\leftleftarrows"]]
   puts [createJavaSnip [createIcon "\\leftrightarrows"]]
   puts [createJavaSnip [createIcon "\\Lleftarrow"]]
   puts [createJavaSnip [createIcon "\\twoheadleftarrow"]]
   puts [createJavaSnip [createIcon "\\leftarrowtail"]]
   puts [createJavaSnip [createIcon "\\looparrowleft"]]
   puts [createJavaSnip [createIcon "\\leftrightharpoons"]]
   puts [createJavaSnip [createIcon "\\curvearrowleft"]]
   puts [createJavaSnip [createIcon "\\circlearrowleft"]]
   puts [createJavaSnip [createIcon "\\Lsh"]]
   puts [createJavaSnip [createIcon "\\upuparrows"]]
   puts [createJavaSnip [createIcon "\\upharpoonleft"]]
   puts [createJavaSnip [createIcon "\\downharpoonleft"]]
   puts [createJavaSnip [createIcon "\\multimap"]]
   puts [createJavaSnip [createIcon "\\leftrightsquigarrow"]]
   puts [createJavaSnip [createIcon "\\looparrowright"]]
   puts [createJavaSnip [createIcon "\\rightleftharpoons"]]
   puts [createJavaSnip [createIcon "\\curvearrowright"]]
   puts [createJavaSnip [createIcon "\\circlearrowright"]]
   puts [createJavaSnip [createIcon "\\Rsh"]]
   puts [createJavaSnip [createIcon "\\downdownarrows"]]
   puts [createJavaSnip [createIcon "\\upharpoonright"]]
   puts [createJavaSnip [createIcon "\\downharpoonright"]]
   puts [createJavaSnip [createIcon "\\rightsquigarrow"]]
   puts [createJavaSnip [createIcon "\\Rrightarrow"]]
   puts [createJavaSnip [createIcon "\\rightrightarrows"]]
   puts [createJavaSnip [createIcon "\\twoheadrightarrow"]]
   puts [createJavaSnip [createIcon "\\restriction"]]
   puts [createJavaSnip [createIcon "\\rightleftarrows"]]
   puts [createJavaSnip [createIcon "\\rightarrowtail"]]
}


proc AmsNegatedArrow {} {
   puts "" 
   puts "--- AmsNegatedArrowMenuC ---"

   puts [createJavaSnip [createIcon "\\nleftarrow"]]
   puts [createJavaSnip [createIcon "\\nrightarrow"]]
   puts [createJavaSnip [createIcon "\\nLeftarrow" "casenLeftarrow.png"] "casenLeftarrow.png"]
   puts [createJavaSnip [createIcon "\\nRightarrow" "casenRightarrow.png"] "casenRightarrow.png"]
   puts [createJavaSnip [createIcon "\\nleftrightarrow"]]
   puts [createJavaSnip [createIcon "\\nLeftrightarrow" "casenLeftrightarrow.png"] "casenLeftrightarrow.png"]
}


proc AmsRelationOne {} {
   puts "" 
   puts "--- AmsRelationOneMenuC ---"

   puts [createJavaSnip [createIcon "\\leqq"]]
   puts [createJavaSnip [createIcon "\\leqslant"]]
   puts [createJavaSnip [createIcon "\\eqslantless"]]
   puts [createJavaSnip [createIcon "\\lesssim"]]
   puts [createJavaSnip [createIcon "\\lessapprox"]]
   puts [createJavaSnip [createIcon "\\approxeq"]]
   puts [createJavaSnip [createIcon "\\lessdot"]]
   puts [createJavaSnip [createIcon "\\lll"]]
   puts [createJavaSnip [createIcon "\\llless"]]
   puts [createJavaSnip [createIcon "\\lessgtr"]]
   puts [createJavaSnip [createIcon "\\lesseqgtr"]]
   puts [createJavaSnip [createIcon "\\lesseqqgtr"]]
   puts [createJavaSnip [createIcon "\\doteqdot"]]
   puts [createJavaSnip [createIcon "\\Doteq" "caseDoteq.png"] "caseDoteq.png"]
   puts [createJavaSnip [createIcon "\\risingdotseq"]]
   puts [createJavaSnip [createIcon "\\fallingdotseq"]]
   puts [createJavaSnip [createIcon "\\backsim"]]
   puts [createJavaSnip [createIcon "\\backsimeq"]]
   puts [createJavaSnip [createIcon "\\subseteqq"]]
   puts [createJavaSnip [createIcon "\\Subset" "caseSubset.png"] "caseSubset.png"]
   puts [createJavaSnip [createIcon "\\sqsubset"]]
   puts [createJavaSnip [createIcon "\\preccurlyeq"]]
   puts [createJavaSnip [createIcon "\\curlyeqprec"]]
   puts [createJavaSnip [createIcon "\\precsim"]]
   puts [createJavaSnip [createIcon "\\precapprox"]]
   puts [createJavaSnip [createIcon "\\vartriangleleft"]]
   puts [createJavaSnip [createIcon "\\trianglelefteq"]]
   puts [createJavaSnip [createIcon "\\vDash" "caseDvDash.png"] "caseDvDash.png"]
   puts [createJavaSnip [createIcon "\\Vvdash"]]
   puts [createJavaSnip [createIcon "\\smallsmile"]]
   puts [createJavaSnip [createIcon "\\smallfrown"]]
   puts [createJavaSnip [createIcon "\\bumpeq"]]
   puts [createJavaSnip [createIcon "\\Bumpeq" "caseBumpeq.png"] "caseBumpeq.png"]
}


proc AmsRelationTwo {} {
   puts "" 
   puts "--- AmsRelationTwoMenuC --"

   puts [createJavaSnip [createIcon "\\geqq"]]
   puts [createJavaSnip [createIcon "\\geqslant"]]
   puts [createJavaSnip [createIcon "\\eqslantgtr"]]
   puts [createJavaSnip [createIcon "\\gtrsim"]]
   puts [createJavaSnip [createIcon "\\gtrapprox"]]
   puts [createJavaSnip [createIcon "\\gtrdot"]]
   puts [createJavaSnip [createIcon "\\ggg"]]
   puts [createJavaSnip [createIcon "\\gggtr"]]
   puts [createJavaSnip [createIcon "\\gtrless"]]
   puts [createJavaSnip [createIcon "\\gtreqless"]]
   puts [createJavaSnip [createIcon "\\gtreqqless"]]
   puts [createJavaSnip [createIcon "\\eqcirc"]]
   puts [createJavaSnip [createIcon "\\circeq"]]
   puts [createJavaSnip [createIcon "\\triangleq"]]
   puts [createJavaSnip [createIcon "\\thicksim"]]
   puts [createJavaSnip [createIcon "\\thickapprox"]]
   puts [createJavaSnip [createIcon "\\supseteqq"]]
   puts [createJavaSnip [createIcon "\\Supset" "caseSupset.png"] "caseSupset.png"]
   puts [createJavaSnip [createIcon "\\sqsupset"]]
   puts [createJavaSnip [createIcon "\\succcurlyeq"]]
   puts [createJavaSnip [createIcon "\\curlyeqsucc"]]
   puts [createJavaSnip [createIcon "\\succsim"]]
   puts [createJavaSnip [createIcon "\\succapprox"]]
   puts [createJavaSnip [createIcon "\\vartriangleright"]]
   puts [createJavaSnip [createIcon "\\trianglerighteq"]]
   puts [createJavaSnip [createIcon "\\Vdash" "caseVVdash.png"] "caseVVdash.png"]
   puts [createJavaSnip [createIcon "\\shortmid"]]
   puts [createJavaSnip [createIcon "\\shortparallel"]]
   puts [createJavaSnip [createIcon "\\between"]]
   puts [createJavaSnip [createIcon "\\pitchfork"]]
   puts [createJavaSnip [createIcon "\\varpropto"]]
   puts [createJavaSnip [createIcon "\\blacktriangleleft"]]
   puts [createJavaSnip [createIcon "\\therefore"]]
   puts [createJavaSnip [createIcon "\\backepsilon"]]
   puts [createJavaSnip [createIcon "\\blacktriangleright"]]
   puts [createJavaSnip [createIcon "\\because"]]
}


proc AmsNegatedRelationOne {} {
   puts "" 
   puts "--- AmsNegatedRelationOneMenuC ---"

   puts [createJavaSnip [createIcon "\\nless"]]
   puts [createJavaSnip [createIcon "\\nleq"]]
   puts [createJavaSnip [createIcon "\\nleqslant"]]
   puts [createJavaSnip [createIcon "\\nleqq"]]
   puts [createJavaSnip [createIcon "\\lneq"]]
   puts [createJavaSnip [createIcon "\\lneqq"]]
   puts [createJavaSnip [createIcon "\\lvertneqq"]]
   puts [createJavaSnip [createIcon "\\lnsim"]]
   puts [createJavaSnip [createIcon "\\lnapprox"]]
   puts [createJavaSnip [createIcon "\\nprec"]]
   puts [createJavaSnip [createIcon "\\npreceq"]]
   puts [createJavaSnip [createIcon "\\precnsim"]]
   puts [createJavaSnip [createIcon "\\precnapprox"]]
   puts [createJavaSnip [createIcon "\\nsim"]]
   puts [createJavaSnip [createIcon "\\nshortmid"]]
   puts [createJavaSnip [createIcon "\\nmid"]]
   puts [createJavaSnip [createIcon "\\nvdash"]]
   puts [createJavaSnip [createIcon "\\nvDash" "casenvDash.png"] "casenvDash.png"]
   puts [createJavaSnip [createIcon "\\ntriangleleft"]]
   puts [createJavaSnip [createIcon "\\ntrianglelefteq"]]
   puts [createJavaSnip [createIcon "\\nsubseteq"]]
   puts [createJavaSnip [createIcon "\\subsetneq"]]
   puts [createJavaSnip [createIcon "\\varsubsetneq"]]
   puts [createJavaSnip [createIcon "\\subsetneqq"]]
   puts [createJavaSnip [createIcon "\\varsubsetneqq"]]
}


proc AmsNegatedRelationTwo {} {
   puts "" 
   puts "--- AmsNegatedRelationTwoMenuC ---"

   puts [createJavaSnip [createIcon "\\ngtr"]]
   puts [createJavaSnip [createIcon "\\ngeq"]]
   puts [createJavaSnip [createIcon "\\ngeqslant"]]
   puts [createJavaSnip [createIcon "\\ngeqq"]]
   puts [createJavaSnip [createIcon "\\gneq"]]
   puts [createJavaSnip [createIcon "\\gneqq"]]
   puts [createJavaSnip [createIcon "\\gvertneqq"]]
   puts [createJavaSnip [createIcon "\\gnsim"]]
   puts [createJavaSnip [createIcon "\\gnapprox"]]
   puts [createJavaSnip [createIcon "\\nsucc"]]
   puts [createJavaSnip [createIcon "\\nsucceq"]]
   puts [createJavaSnip [createIcon "\\succnsim"]]
   puts [createJavaSnip [createIcon "\\succnapprox"]]
   puts [createJavaSnip [createIcon "\\ncong"]]
   puts [createJavaSnip [createIcon "\\nshortparallel"]]
   puts [createJavaSnip [createIcon "\\nparallel"]]
   puts [createJavaSnip [createIcon "\\nvDash" "casenvDash.png"] "casenvDash.png"]
   puts [createJavaSnip [createIcon "\\nVDash" "casecasenVDash.png"] "casecasenVDash.png"]
   puts [createJavaSnip [createIcon "\\ntriangleright"]]
   puts [createJavaSnip [createIcon "\\ntrianglerighteq"]]
   puts [createJavaSnip [createIcon "\\nsupseteq"]]
   puts [createJavaSnip [createIcon "\\nsupseteqq"]]
   puts [createJavaSnip [createIcon "\\supsetneq"]]
   puts [createJavaSnip [createIcon "\\varsupsetneq"]]
   puts [createJavaSnip [createIcon "\\supsetneqq"]]
   puts [createJavaSnip [createIcon "\\varsupsetneqq"]]
}


proc AmsBinaryOperation {} {
   puts "" 
   puts "--- AmsBinaryOperatorMenuC ---"

   puts [createJavaSnip [createIcon "\\dotplus"]]
   puts [createJavaSnip [createIcon "\\smallsetminus"]]
   puts [createJavaSnip [createIcon "\\Cap" "caseCap.png"] "caseCap.png"]
   puts [createJavaSnip [createIcon "\\Cup" "caseCup.png"] "caseCup.png"]
   puts [createJavaSnip [createIcon "\\barwedge"]]
   puts [createJavaSnip [createIcon "\\veebar"]]
   puts [createJavaSnip [createIcon "\\doublebarwedge"]]
   puts [createJavaSnip [createIcon "\\boxminus"]]
   puts [createJavaSnip [createIcon "\\boxtimes"]]
   puts [createJavaSnip [createIcon "\\boxdot"]]
   puts [createJavaSnip [createIcon "\\boxplus"]]
   puts [createJavaSnip [createIcon "\\divideontimes"]]
   puts [createJavaSnip [createIcon "\\ltimes"]]
   puts [createJavaSnip [createIcon "\\rtimes"]]
   puts [createJavaSnip [createIcon "\\leftthreetimes"]]
   puts [createJavaSnip [createIcon "\\rightthreetimes"]]
   puts [createJavaSnip [createIcon "\\curlywedge"]]
   puts [createJavaSnip [createIcon "\\curlyvee"]]
   puts [createJavaSnip [createIcon "\\circleddash"]]
   puts [createJavaSnip [createIcon "\\circledast"]]
   puts [createJavaSnip [createIcon "\\circledcirc"]]
   puts [createJavaSnip [createIcon "\\centerdot"]]
   puts [createJavaSnip [createIcon "\\intercal"]]
}


proc AmsMiscellaneous {} {
   puts "" 
   puts "--- AMS/Miscellaneous ---"

   puts [createJavaSnip [createIcon "\\hbar"]]
   puts [createJavaSnip [createIcon "\\hslash"]]
   puts [createJavaSnip [createIcon "\\vartriangle"]]
   puts [createJavaSnip [createIcon "\\triangledown"]]
   puts [createJavaSnip [createIcon "\\square"]]
   puts [createJavaSnip [createIcon "\\lozenge"]]
   puts [createJavaSnip [createIcon "\\circledS"]]
   puts [createJavaSnip [createIcon "\\angle"]]
   puts [createJavaSnip [createIcon "\\measuredangle"]]
   puts [createJavaSnip [createIcon "\\nexists"]]
   puts [createJavaSnip [createIcon "\\mho"]]
   puts [createJavaSnip [createIcon "\\Finv"]]
   puts [createJavaSnip [createIcon "\\Game"]]
   puts [createJavaSnip [createIcon "\\Bbbk"]]
   puts [createJavaSnip [createIcon "\\backprime"]]
   puts [createJavaSnip [createIcon "\\varnothing"]]
   puts [createJavaSnip [createIcon "\\blacktriangle"]]
   puts [createJavaSnip [createIcon "\\blacktriangledown"]]
   puts [createJavaSnip [createIcon "\\blacksquare"]]
   puts [createJavaSnip [createIcon "\\blacklozenge"]]
   puts [createJavaSnip [createIcon "\\bigstar"]]
   puts [createJavaSnip [createIcon "\\sphericalangle"]]
   puts [createJavaSnip [createIcon "\\complement"]]
   puts [createJavaSnip [createIcon "\\eth"]]
   puts [createJavaSnip [createIcon "\\diagup"]]
   puts [createJavaSnip [createIcon "\\diagdown"]]
}


proc AmsAccent {} {
   puts "" 
   puts "--- AMS/Accent ---"

   puts [createJavaSnip [createIcon "\\dddot{a}"]]
   puts [createJavaSnip [createIcon "\\ddddot{a}"]]
}


proc AmsDelimiter {} {
   puts "" 
   puts "--- AMS/Delimiter ---"

## no icons puts directly
   puts {add(new MathSymbolMenuItemC(editorPane, "\\bigl", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\bigr", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\Bigl", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\Bigr", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\biggl", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\biggr", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\Biggl", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\Biggr", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\lvert", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\rvert", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\lVert", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\rVert", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\ulcorner", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\urcorner", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\llcorner", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\lrcorner", null));}
}


proc AmsSpecial {} {
   puts "" 
   puts "--- AMS/Special ---"
    
   puts {add(new MathSymbolMenuItemC(editorPane, "\\nobreakdash", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\leftroot", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\uproot", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\accentedsymbol", null));}
   puts [createJavaSnip [createIcon "\\xleftarrow{a}"]]
   puts [createJavaSnip [createIcon "\\xrightarrow{a}"]]
   puts [createJavaSnip [createIcon "\\overset{a}{X}"]]
   puts [createJavaSnip [createIcon "\\underset{b}{X}"]]
   puts [createJavaSnip [createIcon "\\dfrac{k}{2}"]] 
   puts {add(new MathSymbolMenuItemC(editorPane, "\\genfrac{ }{ }{ }{ }{ }{ }", "\\genfrac{}{}{}{}{}{}", null));}
   puts [createJavaSnip [createIcon "\\tfrac{k}{2}"]]
   puts [createJavaSnip [createIcon "\\binom{k}{2}"]]
   puts [createJavaSnip [createIcon "\\dbinom{k}{2}"]]
   puts [createJavaSnip [createIcon "\\tbinom{k}{2}"]]
   puts {add(new MathSymbolMenuItemC(editorPane, "\\smash", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\eucal", null));}
   puts [createJavaSnip [createIcon "\\boldsymbol{a}"]]
   puts [createJavaSnip [createIcon "\\text{text}"]]
   puts {add(new MathSymbolMenuItemC(editorPane, "\\intertext", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\substack", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\subarray", null));}
   puts {add(new MathSymbolMenuItemC(editorPane, "\\sideset", null));}
}
  

LatexGreekUppercase
LatexGreekLowercase
LatexBinaryOperator
LatexRelation
LatexArrows
LatexPunctuation
LatexMiscellaneous
LatexVariableSize
LatexLogLike
LatexDelimiter
LatexConstruct
LatexAccent

AmsHebrew
AmsArrow
AmsNegatedArrow
AmsRelationOne
AmsRelationTwo
AmsNegatedRelationOne
AmsNegatedRelationTwo
AmsBinaryOperation
AmsMiscellaneous
AmsAccent
AmsDelimiter
AmsSpecial


puts stderr "Because of Windows, run NamesDifferOnlyInCase.sh and fix the reported names!"

