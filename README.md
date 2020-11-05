# NBody
CS 245 (Data Struct. &amp; Algo) - NBody

DESCRIPTION:
This program simulates the effect that gravitational force, mass, acceleration, and velocity have on moving objects.  The end result of the calculations and animations from this
project is something comparable toa virtual solar system.

STRUCTURE OF THE APPLICATION:
This project follows a model, view, controller (MVC) architecture.  The way that the model, view, and controller interact is described below:
  -Controller: gets data from source (CSV file) and populates model objects
  -Model: provides structure for the application by structuring and accepting data in a way that both the controller and view can understand
  -View: renders the data from the model 

HIERARCHICAL STRUCTURE OF THE APPLICATION:
~NBody
  ~CelestialController
  ~CelestialModel
  ~CelestialView
  ~main
