/**
 * generated by Xtext 2.22.0
 * «»
 */
package org.sctest.scdsl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractGenerator;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.sctest.scdsl.sCDsl.Asset;
import org.sctest.scdsl.sCDsl.Parameter;
import org.sctest.scdsl.sCDsl.Participant;
import org.sctest.scdsl.sCDsl.SContract;
import org.sctest.scdsl.sCDsl.TranRel;
import org.sctest.scdsl.sCDsl.Transaction;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class SCDslGenerator extends AbstractGenerator {
  private String participant = "";
  
  private String asset = "";
  
  private String transaction = "";
  
  private String tranrel = "";
  
  private String assetrel = "";
  
  private String rel = "";
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    Iterable<SContract> _iterable = IteratorExtensions.<SContract>toIterable(Iterators.<SContract>filter(resource.getAllContents(), SContract.class));
    for (final SContract s : _iterable) {
      String _string = s.getPlatform().toString();
      boolean _equals = Objects.equal(_string, "IBM");
      if (_equals) {
        Iterable<Asset> _iterable_1 = IteratorExtensions.<Asset>toIterable(Iterators.<Asset>filter(resource.getAllContents(), Asset.class));
        for (final Asset a : _iterable_1) {
          {
            EList<Parameter> _parameters = a.getParameters();
            for (final Parameter par : _parameters) {
              boolean _isIdentifier = par.isIdentifier();
              boolean _equals_1 = (_isIdentifier == true);
              if (_equals_1) {
                String _upperCase = a.getName().substring(0, 1).toUpperCase();
                String _plus = ((this.asset + "asset ") + _upperCase);
                String _substring = a.getName().substring(1);
                String _plus_1 = (_plus + _substring);
                String _plus_2 = (_plus_1 + " identified by ");
                String _name = par.getName();
                String _plus_3 = (_plus_2 + _name);
                String _plus_4 = (_plus_3 + " {\n");
                this.asset = _plus_4;
              }
            }
            EList<Parameter> _parameters_1 = a.getParameters();
            for (final Parameter par_1 : _parameters_1) {
              String _upperCase_1 = par_1.getType().substring(0, 1).toUpperCase();
              String _plus_5 = ((this.asset + "o ") + _upperCase_1);
              String _substring_1 = par_1.getType().substring(1);
              String _plus_6 = (_plus_5 + _substring_1);
              String _plus_7 = (_plus_6 + " ");
              String _name_1 = par_1.getName();
              String _plus_8 = (_plus_7 + _name_1);
              String _plus_9 = (_plus_8 + "\n");
              this.asset = _plus_9;
            }
            this.asset = (this.asset + "}\n");
          }
        }
        Iterable<Participant> _iterable_2 = IteratorExtensions.<Participant>toIterable(Iterators.<Participant>filter(resource.getAllContents(), Participant.class));
        for (final Participant p : _iterable_2) {
          {
            EList<Parameter> _parameters = p.getParameters();
            for (final Parameter par : _parameters) {
              boolean _isIdentifier = par.isIdentifier();
              boolean _equals_1 = (_isIdentifier == true);
              if (_equals_1) {
                String _upperCase = p.getName().substring(0, 1).toUpperCase();
                String _plus = ((this.participant + "participant ") + _upperCase);
                String _substring = p.getName().substring(1);
                String _plus_1 = (_plus + _substring);
                String _plus_2 = (_plus_1 + " identified by ");
                String _name = par.getName();
                String _plus_3 = (_plus_2 + _name);
                String _plus_4 = (_plus_3 + "{\n");
                this.participant = _plus_4;
              }
            }
            EList<Parameter> _parameters_1 = p.getParameters();
            for (final Parameter par_1 : _parameters_1) {
              String _upperCase_1 = par_1.getType().substring(0, 1).toUpperCase();
              String _plus_5 = ((this.participant + "o ") + _upperCase_1);
              String _substring_1 = par_1.getType().substring(1);
              String _plus_6 = (_plus_5 + _substring_1);
              String _plus_7 = (_plus_6 + " ");
              String _name_1 = par_1.getName();
              String _plus_8 = (_plus_7 + _name_1);
              String _plus_9 = (_plus_8 + "\n");
              this.participant = _plus_9;
            }
            this.participant = (this.participant + "}\n");
          }
        }
        Iterable<Transaction> _iterable_3 = IteratorExtensions.<Transaction>toIterable(Iterators.<Transaction>filter(resource.getAllContents(), Transaction.class));
        for (final Transaction t : _iterable_3) {
          {
            String _upperCase = t.getName().substring(0, 1).toUpperCase();
            String _plus = ((this.transaction + "transaction ") + _upperCase);
            String _substring = t.getName().substring(1);
            String _plus_1 = (_plus + _substring);
            String _plus_2 = (_plus_1 + " {\n");
            this.transaction = _plus_2;
            Iterable<TranRel> _iterable_4 = IteratorExtensions.<TranRel>toIterable(Iterators.<TranRel>filter(resource.getAllContents(), TranRel.class));
            for (final TranRel r : _iterable_4) {
              {
                String _upperCase_1 = r.getFrom().getName().substring(0, 1).toUpperCase();
                String _plus_3 = ((this.transaction + "--> ") + _upperCase_1);
                String _substring_1 = r.getName().toString().substring(1);
                String _plus_4 = (_plus_3 + _substring_1);
                String _plus_5 = (_plus_4 + " ");
                String _lowerCase = r.getFrom().getName().toLowerCase();
                String _plus_6 = (_plus_5 + _lowerCase);
                String _plus_7 = (_plus_6 + "\n");
                this.transaction = _plus_7;
                String _upperCase_2 = r.getTo().getName().substring(0, 1).toUpperCase();
                String _plus_8 = ((this.transaction + "--> ") + _upperCase_2);
                String _substring_2 = r.getTo().getName().substring(1);
                String _plus_9 = (_plus_8 + _substring_2);
                String _plus_10 = (_plus_9 + " ");
                String _lowerCase_1 = r.getTo().getName().toLowerCase();
                String _plus_11 = (_plus_10 + _lowerCase_1);
                String _plus_12 = (_plus_11 + "\n");
                this.transaction = _plus_12;
              }
            }
            this.transaction = (this.transaction + "}\n");
          }
        }
        String _name = s.getName();
        String _plus = (_name + ".cto");
        fsa.generateFile(_plus, this.generateCodeIBM(s));
      } else {
        String _string_1 = s.getPlatform().toString();
        boolean _equals_1 = Objects.equal(_string_1, "Azure");
        if (_equals_1) {
          Iterable<Participant> _iterable_4 = IteratorExtensions.<Participant>toIterable(Iterators.<Participant>filter(resource.getAllContents(), Participant.class));
          for (final Participant p_1 : _iterable_4) {
            String _upperCase = p_1.getName().substring(0, 1).toUpperCase();
            String _plus_1 = (((this.participant + "{ \n") + "\"Name\": ") + _upperCase);
            String _substring = p_1.getName().substring(1);
            String _plus_2 = (_plus_1 + _substring);
            String _plus_3 = (_plus_2 + "\n");
            String _plus_4 = (_plus_3 + "\"Description\": \"...\"");
            String _plus_5 = (_plus_4 + "\n},\n");
            this.participant = _plus_5;
          }
          Iterable<TranRel> _iterable_5 = IteratorExtensions.<TranRel>toIterable(Iterators.<TranRel>filter(resource.getAllContents(), TranRel.class));
          for (final TranRel trel : _iterable_5) {
            this.tranrel = (this.tranrel + trel);
          }
          String _name_1 = s.getName();
          String _plus_6 = (_name_1 + ".json");
          fsa.generateFile(_plus_6, this.generateCodeAzure(s));
        } else {
          Iterable<Participant> _iterable_6 = IteratorExtensions.<Participant>toIterable(Iterators.<Participant>filter(resource.getAllContents(), Participant.class));
          for (final Participant p_2 : _iterable_6) {
            {
              String _upperCase_1 = p_2.getName().substring(0, 1).toUpperCase();
              String _plus_7 = ((this.participant + "struct ") + _upperCase_1);
              String _substring_1 = p_2.getName().substring(1);
              String _plus_8 = (_plus_7 + _substring_1);
              String _plus_9 = (_plus_8 + "{\n");
              this.participant = _plus_9;
              EList<Parameter> _parameters = p_2.getParameters();
              for (final Parameter par : _parameters) {
                String _lowerCase = par.getType().toLowerCase();
                boolean _equals_2 = Objects.equal(_lowerCase, "string");
                if (_equals_2) {
                  String _name_2 = par.getName();
                  String _plus_10 = ((this.participant + "bytes32 ") + _name_2);
                  String _plus_11 = (_plus_10 + ";\n");
                  this.participant = _plus_11;
                } else {
                  String _type = par.getType();
                  String _plus_12 = (this.participant + _type);
                  String _plus_13 = (_plus_12 + " ");
                  String _name_3 = par.getName();
                  String _plus_14 = (_plus_13 + _name_3);
                  String _plus_15 = (_plus_14 + ";\n");
                  this.participant = _plus_15;
                }
              }
              String _lowerCase_1 = p_2.getName().toLowerCase();
              String _plus_16 = ((this.participant + "address ") + _lowerCase_1);
              String _plus_17 = (_plus_16 + "Address;\n } \n");
              this.participant = _plus_17;
            }
          }
          Iterable<Asset> _iterable_7 = IteratorExtensions.<Asset>toIterable(Iterators.<Asset>filter(resource.getAllContents(), Asset.class));
          for (final Asset a_1 : _iterable_7) {
            EList<Parameter> _parameters = a_1.getParameters();
            for (final Parameter par : _parameters) {
              String _type = par.getType();
              String _plus_7 = (this.asset + _type);
              String _plus_8 = (_plus_7 + " public ");
              String _name_2 = par.getName();
              String _plus_9 = (_plus_8 + _name_2);
              String _plus_10 = (_plus_9 + ";\n");
              this.asset = _plus_10;
            }
          }
          Iterable<TranRel> _iterable_8 = IteratorExtensions.<TranRel>toIterable(Iterators.<TranRel>filter(resource.getAllContents(), TranRel.class));
          for (final TranRel trel_1 : _iterable_8) {
            String _name_3 = trel_1.getName();
            String _plus_11 = ("modifier only" + _name_3);
            String _plus_12 = (_plus_11 + " {\n");
            String _plus_13 = (_plus_12 + "require( msg.sender == ");
            String _upperCase_1 = trel_1.getName().substring(0, 1).toUpperCase();
            String _plus_14 = (_plus_13 + _upperCase_1);
            String _substring_1 = trel_1.getName().substring(1);
            String _plus_15 = (_plus_14 + _substring_1);
            String _plus_16 = (_plus_15 + ".");
            String _lowerCase = trel_1.getName().toLowerCase();
            String _plus_17 = (_plus_16 + _lowerCase);
            String _plus_18 = (_plus_17 + "Address);\n_;\n}");
            this.tranrel = _plus_18;
          }
          String _name_4 = s.getName();
          String _plus_19 = (_name_4 + ".sol");
          fsa.generateFile(_plus_19, this.generateCodeSolidity(s));
        }
      }
    }
  }
  
  private CharSequence generateCodeIBM(final SContract s) {
    CharSequence _xblockexpression = null;
    {
      final ArrayList<String> elelist = CollectionLiterals.<String>newArrayList(this.tranrel, this.participant, this.asset, this.transaction);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("namespace ");
      String _replaceAll = s.getName().replaceAll(" ", "_");
      _builder.append(_replaceAll);
      _builder.newLineIfNotEmpty();
      {
        for(final String el : elelist) {
          _builder.append(el);
          _builder.newLineIfNotEmpty();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  private CharSequence generateCodeAzure(final SContract s) {
    CharSequence _xblockexpression = null;
    {
      final ArrayList<String> elelist1 = CollectionLiterals.<String>newArrayList(this.participant);
      final ArrayList<String> elelist2 = CollectionLiterals.<String>newArrayList(this.tranrel);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"ApplicationName\": \"");
      String _replaceAll = s.getName().replaceAll(" ", "");
      _builder.append(_replaceAll, "\t");
      _builder.append("\",");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("\"DisplayName\": \"");
      String _name = s.getName();
      _builder.append(_name, "\t");
      _builder.append("\",");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("\"Description\": \"...\",");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"ApplicationRoles\": ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("[");
      _builder.newLine();
      {
        for(final String el1 : elelist1) {
          _builder.append("\t\t");
          _builder.append(el1, "\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("],");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"Workflows\":");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("[");
      _builder.newLine();
      {
        for(final String el2 : elelist2) {
          _builder.append("\t\t");
          _builder.append(el2, "\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("],");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("\"States\":");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("[");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("\"Name\": \"\",");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"DisplayName\": \"\",");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"Description\": \"...\",");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"PercentComplete\": ,");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"Value\": ,");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"Style\": \"\",");
      _builder.newLine();
      _builder.append("\t\t    ");
      _builder.append("\"Transitions\": [\'[]\'/]");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("]");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  private CharSequence generateCodeSolidity(final SContract s) {
    CharSequence _xblockexpression = null;
    {
      final ArrayList<String> elelist = CollectionLiterals.<String>newArrayList(this.participant, this.asset, this.transaction, this.tranrel);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("\t");
      _builder.append("pragma solidity >=0.4.22 <0.7.0;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("contract ");
      String _replaceAll = s.getName().replaceAll(" ", "_");
      _builder.append(_replaceAll, "\t");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("constructor(){");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      {
        for(final String el : elelist) {
          _builder.append("\t\t");
          _builder.append(el, "\t\t");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
