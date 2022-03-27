//
//  UINavigationController-Extension.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/23/22.
//

import Foundation
import SwiftUI


extension UINavigationController {
    /// Override the UI navigation colors to match the app theme
    override open func viewDidLoad() {
        super.viewDidLoad()
        
        let uiLogoColor = UIColor(AppEnvironment.current.theme.logoColor)
        let standard = UINavigationBarAppearance()
        standard.backgroundColor =  uiLogoColor//When you scroll or you have title (small one)
        
        let compact = UINavigationBarAppearance()
        compact.backgroundColor = uiLogoColor //compact-height
        
        let scrollEdge = UINavigationBarAppearance()
        scrollEdge.backgroundColor = uiLogoColor //When you have large title
        
        navigationBar.standardAppearance = standard
        navigationBar.compactAppearance = compact
        navigationBar.scrollEdgeAppearance = scrollEdge
    }
}
