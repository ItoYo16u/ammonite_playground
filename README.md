# ammonite getting started with coursier

## tl;dr

- coursier\(https://get-coursier.io/docs/cli-installation\)はScalaのライブラリを取得するためのライブラリ
- coursierのCLIであるcsコマンドが便利
  - setup コマンドでjava,sbtが一切入っていない状態からscalaの開発環境を用意できる
  - install コマンドでライブラリをサクッとインストールできる

- ammonite\(https://ammonite.io/#Ammonite\)
  - scala でスクリプトを書くためのさまざまなユーティリティを提供している
  - *.sc ファイルにScalaのスクリプトを書いて動かせる
  - VSCode のScala 拡張機能Metals のサポートが効く
  - Scalaの便利な機能とスクリプト言語のような手軽さを両立させている



## install coursier
```shell script
# install cs(CLI for coursier) locally
curl -fLo cs https://git.io/coursier-cli-"$(uname | tr LD ld)"
chmod +x cs
# install cs globally
./cs install cs
# Then, you have to update Path in .bashrc.
rm cs # cs locally installed is no longer necessary
```

## install ammonite
```shell script
cs install ammonite

amm # enter ammonite Repl
```

## basic usages and commands

### watch file changes and automatically reload
```shell script
amm --watch filename.sc
```

### run sc file

```hello.sc
@main
def main()= {
  println("hello ammonite!")
}
```

```shell script
amm hello.sc
# => hello ammonite!
```

### run sc file with params
```hello.sc
@main
def main(name:String,number:Int) = {
  println(s"hello $name,number: $number")
}
```

```shell script
amm hello.sc johnDoe 0
#=> hello johnDoe,number: 0
```

Source code without @main like below also works.

```hello_v2.sc
println("hello ammonite!")
```

### run one of entrypoints annotated with @main

```example.sc

@main
def prodMain()={
  println("this is for prod")
}

@main debugMain()={
  println("this is for debug")
}

```

```shell script
amm example.sc debugMain
```

### import external libraries

#### import ivy libs

If you want to import "hoge %% foo % varsion"
Change `%` to `:` like below.

```main.sc
import $ivy.`org.scalaz::scalaz-core:7.2.7`
import scalaz._
```

Multiple libraries can be loaded like bellow.

```
import $ivy.{
  `hoge::fuga:piyo`,
  `foo::bar:baz`
}
```