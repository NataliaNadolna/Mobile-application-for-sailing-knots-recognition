# Mobile application for sailing knots recognition

## Table of Contents

- [Dataset](#Dataset)
- [Models](#Classification-models)
- [Research](#Research)
<!-- - [Results](#Results)-->
- [Mobile application](#Mobile-application)

## Dataset
Created custom dataset of 9 classes of knots: 
1. Flagowy,
2. Osemka,
3. Prosty,
4. Ratowniczy,
5. Refowy,
6. Rozkowy,
7. Szotowy,
8. Wyblinka,
9. Zwykly.

In each class there are between 1650 and 2000 images. Data devided into training (70%), validation (20%) and testing (10%).

Created second testing dataset (about 200-250 images prer class) with knots, which were tied on different ropes.

### Knot detection
All images were cut into shape of the knot. For this purpose trained neural network to detect knot on image (usung Roboflow).

## Classification models
Compered 4 models in classification task:
- MobileNetV3Large
- RegNetY800FM
- custom convolutional model:
```python
========================================================================================================================
Layer (type (var_name))                  Input Shape          Output Shape         Param #              Trainable
========================================================================================================================
Model (Model)                            [32, 3, 224, 224]    [32, 9]              --                   True
├─Sequential (block_1)                   [32, 3, 224, 224]    [32, 32, 112, 112]   --                   True
│    └─Conv2d (0)                        [32, 3, 224, 224]    [32, 32, 224, 224]   896                  True
│    └─ReLU (1)                          [32, 32, 224, 224]   [32, 32, 224, 224]   --                   --
│    └─Dropout (2)                       [32, 32, 224, 224]   [32, 32, 224, 224]   --                   --
│    └─Conv2d (3)                        [32, 32, 224, 224]   [32, 32, 224, 224]   9,248                True
│    └─ReLU (4)                          [32, 32, 224, 224]   [32, 32, 224, 224]   --                   --
│    └─MaxPool2d (5)                     [32, 32, 224, 224]   [32, 32, 112, 112]   --                   --
├─Sequential (block_2)                   [32, 32, 112, 112]   [32, 32, 56, 56]     --                   True
│    └─Conv2d (0)                        [32, 32, 112, 112]   [32, 32, 112, 112]   9,248                True
│    └─ReLU (1)                          [32, 32, 112, 112]   [32, 32, 112, 112]   --                   --
│    └─Dropout (2)                       [32, 32, 112, 112]   [32, 32, 112, 112]   --                   --
│    └─Conv2d (3)                        [32, 32, 112, 112]   [32, 32, 112, 112]   9,248                True
│    └─ReLU (4)                          [32, 32, 112, 112]   [32, 32, 112, 112]   --                   --
│    └─MaxPool2d (5)                     [32, 32, 112, 112]   [32, 32, 56, 56]     --                   --
├─Sequential (block_3)                   [32, 32, 56, 56]     [32, 32, 28, 28]     --                   True
│    └─Conv2d (0)                        [32, 32, 56, 56]     [32, 32, 56, 56]     9,248                True
│    └─ReLU (1)                          [32, 32, 56, 56]     [32, 32, 56, 56]     --                   --
│    └─Dropout (2)                       [32, 32, 56, 56]     [32, 32, 56, 56]     --                   --
│    └─Conv2d (3)                        [32, 32, 56, 56]     [32, 32, 56, 56]     9,248                True
│    └─ReLU (4)                          [32, 32, 56, 56]     [32, 32, 56, 56]     --                   --
│    └─MaxPool2d (5)                     [32, 32, 56, 56]     [32, 32, 28, 28]     --                   --
├─Sequential (classifier)                [32, 32, 28, 28]     [32, 9]              --                   True
│    └─Flatten (0)                       [32, 32, 28, 28]     [32, 25088]          --                   --
│    └─Linear (1)                        [32, 25088]          [32, 9]              225,801              True
========================================================================================================================
Total params: 272,937
Trainable params: 272,937
Non-trainable params: 0
Total mult-adds (G): 25.58
========================================================================================================================
Input size (MB): 19.27
Forward/backward pass size (MB): 1078.99
Params size (MB): 1.09
Estimated Total Size (MB): 1099.35
========================================================================================================================
```
- custom model with shortcut connetcions:
```python
========================================================================================================================
Layer (type (var_name))                  Input Shape          Output Shape         Param #              Trainable
========================================================================================================================
ResNet (ResNet)                          [32, 3, 224, 224]    [32, 9]              --                   True
├─Conv2d (conv1)                         [32, 3, 224, 224]    [32, 64, 112, 112]   9,408                True
├─BatchNorm2d (bn1)                      [32, 32, 112, 112]   [32, 64, 112, 112]   128                  True
├─Sequential (layer1)                    [32, 32, 56, 56]     [32, 32, 56, 56]     --                   True
│    └─BasicBlock (0)                    [32, 32, 56, 56]     [32, 32, 56, 56]     --                   True
│    │    └─Conv2d (conv1)               [32, 32, 56, 56]     [32, 32, 56, 56]     18,432               True
│    │    └─BatchNorm2d (bn1)            [32, 32, 56, 56]     [32, 32, 56, 56]     64                   True
│    │    └─Conv2d (conv2)               [32, 32, 56, 56]     [32, 32, 56, 56]     9,216                True
│    │    └─BatchNorm2d (bn2)            [32, 32, 56, 56]     [32, 32, 56, 56]     64                   True
│    │    └─Sequential (shortcut)        [32, 32, 56, 56]     [32, 32, 56, 56]     2,112                True
│    └─BasicBlock (1)                    [32, 32, 56, 56]     [32, 32, 56, 56]     --                   True
│    │    └─Conv2d (conv1)               [32, 32, 56, 56]     [32, 32, 56, 56]     9,216                True
│    │    └─BatchNorm2d (bn1)            [32, 32, 56, 56]     [32, 32, 56, 56]     64                   True
│    │    └─Conv2d (conv2)               [32, 32, 56, 56]     [32, 32, 56, 56]     9,216                True
│    │    └─BatchNorm2d (bn2)            [32, 32, 56, 56]     [32, 32, 56, 56]     64                   True
│    │    └─Sequential (shortcut)        [32, 32, 56, 56]     [32, 32, 56, 56]     --                   --
├─Sequential (layer2)                    [32, 32, 56, 56]     [32, 64, 28, 28]     --                   True
│    └─BasicBlock (0)                    [32, 32, 56, 56]     [32, 64, 28, 28]     --                   True
│    │    └─Conv2d (conv1)               [32, 32, 56, 56]     [32, 64, 28, 28]     18,432               True
│    │    └─BatchNorm2d (bn1)            [32, 64, 28, 28]     [32, 64, 28, 28]     128                  True
│    │    └─Conv2d (conv2)               [32, 64, 28, 28]     [32, 64, 28, 28]     36,864               True
│    │    └─BatchNorm2d (bn2)            [32, 64, 28, 28]     [32, 64, 28, 28]     128                  True
│    │    └─Sequential (shortcut)        [32, 32, 56, 56]     [32, 64, 28, 28]     2,176                True
│    └─BasicBlock (1)                    [32, 64, 28, 28]     [32, 64, 28, 28]     --                   True
│    │    └─Conv2d (conv1)               [32, 64, 28, 28]     [32, 64, 28, 28]     36,864               True
│    │    └─BatchNorm2d (bn1)            [32, 64, 28, 28]     [32, 64, 28, 28]     128                  True
│    │    └─Conv2d (conv2)               [32, 64, 28, 28]     [32, 64, 28, 28]     36,864               True
│    │    └─BatchNorm2d (bn2)            [32, 64, 28, 28]     [32, 64, 28, 28]     128                  True
│    │    └─Sequential (shortcut)        [32, 64, 28, 28]     [32, 64, 28, 28]     --                   --
├─Sequential (layer3)                    [32, 64, 28, 28]     [32, 128, 14, 14]    --                   True
│    └─BasicBlock (0)                    [32, 64, 28, 28]     [32, 128, 14, 14]    --                   True
│    │    └─Conv2d (conv1)               [32, 64, 28, 28]     [32, 128, 14, 14]    73,728               True
│    │    └─BatchNorm2d (bn1)            [32, 128, 14, 14]    [32, 128, 14, 14]    256                  True
│    │    └─Conv2d (conv2)               [32, 128, 14, 14]    [32, 128, 14, 14]    147,456              True
│    │    └─BatchNorm2d (bn2)            [32, 128, 14, 14]    [32, 128, 14, 14]    256                  True
│    │    └─Sequential (shortcut)        [32, 64, 28, 28]     [32, 128, 14, 14]    8,448                True
│    └─BasicBlock (1)                    [32, 128, 14, 14]    [32, 128, 14, 14]    --                   True
│    │    └─Conv2d (conv1)               [32, 128, 14, 14]    [32, 128, 14, 14]    147,456              True
│    │    └─BatchNorm2d (bn1)            [32, 128, 14, 14]    [32, 128, 14, 14]    256                  True
│    │    └─Conv2d (conv2)               [32, 128, 14, 14]    [32, 128, 14, 14]    147,456              True
│    │    └─BatchNorm2d (bn2)            [32, 128, 14, 14]    [32, 128, 14, 14]    256                  True
│    │    └─Sequential (shortcut)        [32, 128, 14, 14]    [32, 128, 14, 14]    --                   --
├─Sequential (layer4)                    [32, 128, 14, 14]    [32, 256, 7, 7]      --                   True
│    └─BasicBlock (0)                    [32, 128, 14, 14]    [32, 256, 7, 7]      --                   True
│    │    └─Conv2d (conv1)               [32, 128, 14, 14]    [32, 256, 7, 7]      294,912              True
│    │    └─BatchNorm2d (bn1)            [32, 256, 7, 7]      [32, 256, 7, 7]      512                  True
│    │    └─Conv2d (conv2)               [32, 256, 7, 7]      [32, 256, 7, 7]      589,824              True
│    │    └─BatchNorm2d (bn2)            [32, 256, 7, 7]      [32, 256, 7, 7]      512                  True
│    │    └─Sequential (shortcut)        [32, 128, 14, 14]    [32, 256, 7, 7]      33,280               True
│    └─BasicBlock (1)                    [32, 256, 7, 7]      [32, 256, 7, 7]      --                   True
│    │    └─Conv2d (conv1)               [32, 256, 7, 7]      [32, 256, 7, 7]      589,824              True
│    │    └─BatchNorm2d (bn1)            [32, 256, 7, 7]      [32, 256, 7, 7]      512                  True
│    │    └─Conv2d (conv2)               [32, 256, 7, 7]      [32, 256, 7, 7]      589,824              True
│    │    └─BatchNorm2d (bn2)            [32, 256, 7, 7]      [32, 256, 7, 7]      512                  True
│    │    └─Sequential (shortcut)        [32, 256, 7, 7]      [32, 256, 7, 7]      --                   --
├─Linear (linear)                        [32, 256]            [32, 9]              2,313                True
========================================================================================================================
Total params: 2,817,289
Trainable params: 2,817,289
Non-trainable params: 0
Total mult-adds (Units.GIGABYTES): 18.47
========================================================================================================================
Input size (MB): 19.27
Forward/backward pass size (MB): 892.73
Params size (MB): 11.27
Estimated Total Size (MB): 923.27
========================================================================================================================
```
## Research
Compared:
- hiperparameters
    - epochs: 5, 10, 15, 20
    - batch size: 16, 32, 64
    - learning rate: 0.0001, 0.0005, 0.001
- Transfer Learning
- augmentation
    - traditional:
        - reflection;
        - blur;
        - color;
        - gray;
    - advanced:
      - DCGAN (about 50 generated images per class).

## Mobile application
The best model was implemented in mobile application for sailing knots recognition.
- Intro activity:
  ![](https://github.com/NataliaNadolna/Mobile-application-for-sailing-knots-recognition/blob/main/app/screenshots/intro.jpg =250x)
- Recognition activity:
  ![](https://github.com/NataliaNadolna/Mobile-application-for-sailing-knots-recognition/blob/main/app/screenshots/recognition.jpg =250x)
- List activity:
  ![](https://github.com/NataliaNadolna/Mobile-application-for-sailing-knots-recognition/blob/main/app/screenshots/list_of_knots.jpg =250x)
- Knot activity:
  ![](https://github.com/NataliaNadolna/Mobile-application-for-sailing-knots-recognition/blob/main/app/screenshots/detail_of_knot.jpg =250x)


