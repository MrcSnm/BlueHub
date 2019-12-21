# BlueHub
This is an interproject for connecting PS/2 Keyboard, Mouse or any other device to an Arduino, using Arduino as it were an Hub, then, converting it to bluetooth and getting the input on Android

<h1>Reasoning:</h1>
<ul>
  <li>Start bluetooth connection on smartphone</li>
  <li>Create one input listener</li>
  <li>Get system signature for the Application</li>
  <li>Start bluetooth pairing with the Arduino</li>
  <li>Send keycode from Arduino via bluetooth to the Application</li>
  <li>Read this keycode and execute KeyInjection</li>
</ul>
