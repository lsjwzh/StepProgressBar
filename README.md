StepProgressBar Project
=======================

一个如ios音量\亮度 大小调节显示的进度条控件。

<img src="./preview/section.gif">

Usage
-----
```java

        mStepBar = (StepProgressBar) findViewById(R.id.step_bar);
        mStepBar.setProgressStep(9);
```

```xml

    <com.lsjwzh.widget.StepProgressBar
        android:id="@+id/step_bar2"
        android:layout_width="match_parent"
        android:layout_height="10dp"
        android:background="@color/colorAccent"
        android:padding="1dp"
        app:spb_step_space_width="1dp"
        app:spb_step_color="@color/progress" />
```
    
License
-------

    Copyright 2017 lsjwzh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.